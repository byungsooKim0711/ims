package org.kimbs.ims.api.kakao.handler;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class ImsExceptionHandler {

    @ExceptionHandler(ImsServiceKeyException.class)
    public ImsCommonRes<Void> serviceKeyException(ImsServiceKeyException e, HttpServletRequest request, HttpServletResponse response) {
        return ImsCommonRes.<Void>builder()
                .code(ResponseCode.UNKNOWN_SERVICE_KEY_EXCEPTION)
                .build();
    }

    @ExceptionHandler(ImsTooLongMessageException.class)
    public ImsCommonRes<Void> tooLongMessageException(ImsServiceKeyException e, HttpServletRequest request, HttpServletResponse response) {
        return ImsCommonRes.<Void>builder()
                .code(ResponseCode.TOO_LONG_MESSAGE_EXCEPTION)
                .build();
    }

}
