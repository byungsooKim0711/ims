package org.kimbs.ims.api.kakao.handler;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.*;
import org.kimbs.ims.protocol.ImsApiResult;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ImsExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ImsApiResult<Void>> handle(WebExchangeBindException e, ServerWebExchange exchange) {
        String errorMessage = e.getAllErrors().get(0).getDefaultMessage();

        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            if (bindingResult.getFieldError() != null) {
                return Mono.just(ImsApiResult.failed(ResponseCode.BAD_REQUEST, errorMessage));
            }
        }

        return Mono.just(ImsApiResult.failed(ResponseCode.BAD_REQUEST, errorMessage));
    }

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ImsApiResult<Void>> handle(ServerWebInputException e, ServerWebExchange exchange) {
        return Mono.just(ImsApiResult.failed(ResponseCode.BAD_REQUEST));
    }

    @ExceptionHandler(ImsServiceKeyException.class)
    public Mono<ImsApiResult<Void>> handle(ImsServiceKeyException e, ServerWebExchange exchange) {
        return Mono.just(ImsApiResult.failed(ResponseCode.UNKNOWN_SERVICE_KEY_EXCEPTION));
    }

    @ExceptionHandler(ImsTooLongMessageException.class)
    public Mono<ImsApiResult<Void>> handle(ImsTooLongMessageException e, ServerWebExchange exchange) {
        return Mono.just(ImsApiResult.failed(ResponseCode.TOO_LONG_MESSAGE_EXCEPTION));
    }

    @ExceptionHandler(ImsMandatoryException.class)
    public Mono<ImsApiResult<Void>> handle(ImsMandatoryException e, ServerWebExchange exchange) {
        return Mono.just(ImsApiResult.failed(ResponseCode.MANDATORY_EXCEPTION));
    }

    @ExceptionHandler(ImsDuplicateMsgUidException.class)
    public Mono<ImsApiResult<Void>> handle(ImsDuplicateMsgUidException e, ServerWebExchange exchange) {
        return Mono.just(ImsApiResult.failed(ResponseCode.IMS_DUPLICATE_MSG_UID_EXCEPTION));
    }

    @ExceptionHandler(ImsKafkaSendException.class)
    public Mono<ImsApiResult<Void>> handle(ImsKafkaSendException e, ServerWebExchange exchange) {
        return Mono.just(ImsApiResult.failed(ResponseCode.SERVICE_SERVER_ERROR));
    }


    @ExceptionHandler(Exception.class)
    public Mono<ImsApiResult<Void>> handle(Exception e, ServerWebExchange exchange) {
        log.error("Unknown exception occurred.", e);
        return Mono.just(ImsApiResult.failed(ResponseCode.SERVICE_SERVER_ETC_ERROR));
    }
}
