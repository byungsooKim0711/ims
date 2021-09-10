package org.kimbs.ims.exception;

public class ImsBadRequestException extends RuntimeException {

    private static final long serialVersionUID = -136703957977103613L;

    public ImsBadRequestException() {
        super();
    }

    public ImsBadRequestException(String message) {
        super(message);
    }

    public ImsBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImsBadRequestException(Throwable cause) {
        super(cause);
    }

    protected ImsBadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
