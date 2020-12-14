package org.kimbs.ims.exception;

public class ImsDuplicateMsgUidException extends RuntimeException {

    private static final long serialVersionUID = 6037712178498564054L;

    public ImsDuplicateMsgUidException() {
        super();
    }

    public ImsDuplicateMsgUidException(String message) {
        super(message);
    }

    public ImsDuplicateMsgUidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImsDuplicateMsgUidException(Throwable cause) {
        super(cause);
    }

    protected ImsDuplicateMsgUidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
