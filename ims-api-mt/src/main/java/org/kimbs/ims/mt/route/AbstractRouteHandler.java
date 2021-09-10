package org.kimbs.ims.mt.route;

import org.kimbs.ims.mt.config.ApiMtConfig;
import org.kimbs.ims.mt.service.KafkaService;
import org.kimbs.ims.protocol.ImsApiResult;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

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
                .doOnNext(message -> {
                    this.checkMandatory(message);
                    this.checkDuplicateMsgUid(message);
                    this.send(message);
                    this.log(message);
                })
                .flatMap(req -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(ImsApiResult.succeed(ResponseCode.SUCCESS)))
                .onErrorResume(this::onException);
    }

    protected abstract ImsPacket<M> convert(R request);

    protected abstract void validation(R request);

    protected abstract void checkMandatory(ImsPacket<M> message);
    protected abstract void checkDuplicateMsgUid(ImsPacket<M> message);

    protected abstract void send(ImsPacket<M> message);

    protected abstract Mono<ServerResponse> onException(Throwable e);

    protected abstract void log(ImsPacket<M> message);
}
