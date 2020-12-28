package org.kimbs.ims.exception;

public class ImsMsgUidException extends RuntimeException {

    private static final long serialVersionUID = 5894158203657891627L;

    public ImsMsgUidException() {
        super();
    }

    public ImsMsgUidException(String message) {
        super(message);
    }

    public ImsMsgUidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImsMsgUidException(Throwable cause) {
        super(cause);
    }

    protected ImsMsgUidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
