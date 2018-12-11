package pers.yurwisher.grabber;

/**
 * @author yq
 * @date 2018/12/11 13:48
 * @description grab exception
 * @since V1.0.0
 */
public class GrabException extends RuntimeException {
    private static final long serialVersionUID = 164811548081347271L;

    public GrabException() {
    }

    public GrabException(String message) {
        super(message);
    }

    public GrabException(String message, Throwable cause) {
        super(message, cause);
    }

    public GrabException(Throwable cause) {
        super(cause);
    }

    public GrabException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
