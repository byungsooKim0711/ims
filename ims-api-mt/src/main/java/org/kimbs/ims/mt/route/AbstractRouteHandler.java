package org.kimbs.ims.mt.route;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

public abstract class AbstractRouteHandler<R, M> {

    protected final Validator validator;

    protected AbstractRouteHandler(Validator validator) {
        this.validator = validator;
    }

    public Mono<ServerResponse> sendMessage(Mono<R> request) {
        return request.doOnNext(this::validation)
                .flatMap(req -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("kbstest"))
                .onErrorResume(this::onException);
    }

    protected abstract void validation(R request);

    protected abstract M convert(R request);

    protected abstract void checkMandatory(M message);

    protected abstract void checkDuplicateMsgUid(M message);
    protected abstract void send(M message);
    protected abstract Mono<ServerResponse> onException(Throwable e);

    protected abstract void log(M message);
}
