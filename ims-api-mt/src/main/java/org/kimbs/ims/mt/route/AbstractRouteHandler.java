package org.kimbs.ims.mt.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.TimeoutException;
import org.kimbs.ims.exception.ImsBadRequestException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.exception.NotSupportMessageType;
import org.kimbs.ims.mt.config.ApiMtConfig;
import org.kimbs.ims.mt.service.KafkaService;
import org.kimbs.ims.protocol.ImsApiResult;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.http.MediaType;
import org.springframework.kafka.KafkaException;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

import javax.validation.Validator;

@Slf4j
public abstract class AbstractRouteHandler<R, M> {

    protected final Validator validator;
    protected final ApiMtConfig config;
    protected final KafkaService kafkaService;

    protected AbstractRouteHandler(Validator validator, ApiMtConfig config, KafkaService kafkaService) {
        this.validator = validator;
        this.config = config;
        this.kafkaService = kafkaService;
    }

    public Mono<ServerResponse> sendMessage(Mono<R> request) {
        return request.doOnNext(this::validation)
                .map(this::convert)
                .flatMap(message -> {
                    this.checkMandatory(message);
                    this.checkDuplicateMsgUid(message);
                    return this.send(message)
                            .doOnSubscribe(subscription -> this.log(message));
                })
                .flatMap(req -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(ImsApiResult.succeed(ResponseCode.SUCCESS)))
                .onErrorResume(this::onException);
    }

    protected abstract ImsPacket<M> convert(R request);

    protected void validation(R request) {
        validator.validate(request)
                .stream()
                .findAny()
                .ifPresent(v -> {
                    throw new ImsBadRequestException(v.getMessage());
                });
    }

    protected abstract void checkMandatory(ImsPacket<M> message);
    protected abstract void checkDuplicateMsgUid(ImsPacket<M> message);
    protected abstract Mono<SenderResult<Void>> send(ImsPacket<M> message);
    protected abstract void log(ImsPacket<M> message);

    protected  Mono<ServerResponse> onException(Throwable e) {
        // TODO: exception logging 필요함. request 데이터를 어떻게 가져올 수 있을까...

        if (e instanceof ImsBadRequestException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.BAD_REQUEST, e.getMessage()));
        } else if (e instanceof NotSupportMessageType) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.BAD_REQUEST, e.getMessage()));
        } else if (e instanceof ServerWebInputException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.BAD_REQUEST));
        } else if (e instanceof ImsTooLongMessageException) {
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.TOO_LONG_MESSAGE_EXCEPTION, e.getMessage()));
        } else if (e instanceof KafkaException || e instanceof TimeoutException) {
            log.error("kafka exception.", e);
            return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.SERVICE_SERVER_ERROR));
        }

        log.error("unexpected exception occurred.", e);
        return ServerResponse.ok().bodyValue(ImsApiResult.failed(ResponseCode.SERVICE_SERVER_ETC_ERROR));
    }
}
