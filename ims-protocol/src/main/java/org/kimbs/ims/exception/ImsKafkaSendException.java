package org.kimbs.ims.exception;

public class ImsKafkaSendException extends RuntimeException {
    private static final long serialVersionUID = 7897332401737505992L;

    public ImsKafkaSendException() {
        super();
    }

    public ImsKafkaSendException(String message) {
        super(message);
    }

    public ImsKafkaSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImsKafkaSendException(Throwable cause) {
        super(cause);
    }

    protected ImsKafkaSendException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
