package org.kimbs.ims.exception;

public class ImsServiceKeyException extends RuntimeException {

    private static final long serialVersionUID = -5792617787248164464L;

    public ImsServiceKeyException() {
        super();
    }

    public ImsServiceKeyException(String message) {
        super(message);
    }

    public ImsServiceKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImsServiceKeyException(Throwable cause) {
        super(cause);
    }

    protected ImsServiceKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
