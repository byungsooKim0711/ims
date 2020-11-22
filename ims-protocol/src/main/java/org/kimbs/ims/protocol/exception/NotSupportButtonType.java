package org.kimbs.ims.protocol.exception;

public class NotSupportButtonType extends RuntimeException {

    private static final long serialVersionUID = 6275916426009854051L;

    public NotSupportButtonType() {
        super();
    }

    public NotSupportButtonType(String message) {
        super(message);
    }

    public NotSupportButtonType(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportButtonType(Throwable cause) {
        super(cause);
    }

    protected NotSupportButtonType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
