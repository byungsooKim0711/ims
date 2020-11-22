package org.kimbs.ims.protocol.exception;

public class ServiceKeyException extends RuntimeException {

    public ServiceKeyException() {
        super();
    }

    public ServiceKeyException(String message) {
        super(message);
    }

    public ServiceKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceKeyException(Throwable cause) {
        super(cause);
    }

    protected ServiceKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
