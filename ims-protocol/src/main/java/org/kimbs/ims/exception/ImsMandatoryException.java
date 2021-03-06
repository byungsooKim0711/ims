package org.kimbs.ims.exception;

public class ImsMandatoryException extends RuntimeException {

    private static final long serialVersionUID = -8527137250256018259L;

    public ImsMandatoryException() {
        super();
    }

    public ImsMandatoryException(String message) {
        super(message);
    }

    public ImsMandatoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImsMandatoryException(Throwable cause) {
        super(cause);
    }

    protected ImsMandatoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
