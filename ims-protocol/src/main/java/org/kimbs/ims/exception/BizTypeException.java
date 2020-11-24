package org.kimbs.ims.exception;

public class BizTypeException extends RuntimeException {

    private static final long serialVersionUID = -5149092598463268889L;

    public BizTypeException() {
        super();
    }

    public BizTypeException(String message) {
        super(message);
    }

    public BizTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizTypeException(Throwable cause) {
        super(cause);
    }

    protected BizTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
