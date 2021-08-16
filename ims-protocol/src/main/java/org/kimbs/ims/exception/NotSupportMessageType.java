package org.kimbs.ims.exception;

public class NotSupportMessageType extends RuntimeException {

    private static final long serialVersionUID = 9044511647065184108L;

    public NotSupportMessageType() {
        super();
    }

    public NotSupportMessageType(String message) {
        super(message);
    }

    public NotSupportMessageType(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportMessageType(Throwable cause) {
        super(cause);
    }

    protected NotSupportMessageType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
