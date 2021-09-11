package org.kimbs.ims.api.kakao.route;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.config.ApiKakaoConfig;
import org.kimbs.ims.api.kakao.service.KafkaService;
import org.kimbs.ims.exception.*;
import org.kimbs.ims.protocol.ImsApiResult;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.http.MediaType;
import org.springframework.kafka.KafkaException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

/**
 * R: Request Message
 * M: Convert Message
 */
@Slf4j
public abstract class AbstractRouteHandler<R, M> {

    protected final Validator validator;
    protected final ApiKakaoConfig config;
    protected final KafkaService kafkaService;

    public AbstractRouteHandler(Validator validator, ApiKakaoConfig config, KafkaService kafkaService) {
        this.validator = validator;
        this.config = config;
        this.kafkaService = kafkaService;
    }

    public Mono<ServerResponse> sendMessage(Mono<R> request) {
        return request.doOnNext(this::validation)
                .map(this::convert)
                .doOnNext(message -> {
                    this.checkMandatory(message);
                    this.checkSenderKeyAndTemplate(message);
                    this.checkDuplicateMsgUid(message);
                    this.send(message);
                    this.log(message);
                })
                .flatMap(req -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(ImsApiResult.succeed(ResponseCode.SUCCESS)))
                .onErrorResume(this::onException);
    }

    protected void validation(R request) {
        validator.validate(request)
                .stream()
                .findAny()
                .ifPresent(v -> {
                    throw new ImsBadRequestException(v.getMessage());
                });
    }
    protected abstract ImsPacket<M> convert(R request);
    protected abstract void checkMandatory(ImsPacket<M> message) throws ImsMandatoryException;
    protected abstract void checkSenderKeyAndTemplate(ImsPacket<M> message);
    protected abstract void checkDuplicateMsgUid(ImsPacket<M> message);
    protected abstract void send(ImsPacket<M> message);
    protected abstract void log(ImsPacket<M> message);

    protected Mono<ServerResponse> onException(Throwable e) {
        if (e instanceof WebExchangeBindException) {
            WebExchangeBindException exception = ((WebExchangeBindException) e);

            BindingResult bindingResult = exception.getBindingResult();
            if (bindingResult.hasErrors()) {
                if (bindingResult.getFieldError() != null) {
                    return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.BAD_REQUEST, e.getMessage()));
                }
            }

            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.BAD_REQUEST, e.getMessage()));
        } else if (e instanceof ServerWebInputException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.BAD_REQUEST));
        } else if (e instanceof ImsBadRequestException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.BAD_REQUEST, e.getMessage()));
        } else if (e instanceof NotSupportMessageType) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.NOT_SUPPORT_MESSAGE_TYPE_EXCEPTION, e.getMessage()));
        } else if (e instanceof ImsServiceKeyException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.UNKNOWN_SERVICE_KEY_EXCEPTION));
        } else if (e instanceof ImsTooLongMessageException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.TOO_LONG_MESSAGE_EXCEPTION));
        } else if (e instanceof ImsMandatoryException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.MANDATORY_EXCEPTION, e.getMessage()));
        } else if (e instanceof ImsDuplicateMsgUidException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.IMS_DUPLICATE_MSG_UID_EXCEPTION));
        } else if (e instanceof KafkaException) {
            log.error("kafka exception.", e);
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.SERVICE_SERVER_ERROR));
        }

        log.error("unexpected exception occurred.", e);
        return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.SERVICE_SERVER_ETC_ERROR));
    }
}
