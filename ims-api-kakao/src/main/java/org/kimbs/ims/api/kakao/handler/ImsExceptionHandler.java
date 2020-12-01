package org.kimbs.ims.api.kakao.handler;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ImsExceptionHandler {

    @ExceptionHandler(ImsServiceKeyException.class)
    public Mono<ImsCommonRes<Void>> handle(ImsServiceKeyException e, ServerWebExchange exchange) {
        return Mono.just(
                ImsCommonRes.<Void>builder()
                        .code(ResponseCode.UNKNOWN_SERVICE_KEY_EXCEPTION)
                        .build());
    }

    @ExceptionHandler(ImsTooLongMessageException.class)
    public Mono<ImsCommonRes<Void>> handle(ImsTooLongMessageException e, ServerWebExchange exchange) {
        return Mono.just(
                ImsCommonRes.<Void>builder()
                        .code(ResponseCode.TOO_LONG_MESSAGE_EXCEPTION)
                        .build());
    }

    @ExceptionHandler(ImsMandatoryException.class)
    public Mono<ImsCommonRes<Void>> handle(ImsMandatoryException e, ServerWebExchange exchange) {
        return Mono.just(
                ImsCommonRes.<Void>builder()
                        .code(ResponseCode.MANDATORY_EXCEPTION)
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public Mono<ImsCommonRes<Void>> handle(Exception e, ServerWebExchange exchange) {
        log.error("Unknown exception occurred.", e);
        return Mono.just(
                ImsCommonRes.<Void>builder()
                        .code(ResponseCode.SERVICE_SERVER_ERROR)
                        .build());
    }
}
