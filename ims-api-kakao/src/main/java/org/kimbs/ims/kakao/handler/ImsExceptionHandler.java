package org.kimbs.ims.kakao.handler;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.kimbs.ims.protocol.exception.ServiceKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class ImsExceptionHandler {

    @ExceptionHandler(ServiceKeyException.class)
    public ImsCommonRes<Void> serviceKeyException(ServiceKeyException e, HttpServletRequest request, HttpServletResponse response) {
        return ImsCommonRes.<Void>builder()
                .code(ResponseCode.UNKNOWN_SERVICE_KEY_EXCEPTION)
                .build();
    }
}
