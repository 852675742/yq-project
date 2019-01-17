package pers.yurwisher.syncmanager.exception;

/**
 * @author yq
 * @date 2019/01/14 17:36
 * @description 同步异常
 * @since V1.0.0
 */
public class SyncException extends RuntimeException {

    public SyncException() {
    }

    public SyncException(String message) {
        super(message);
    }

    public SyncException(String message, Throwable cause) {
        super(message, cause);
    }

    public SyncException(Throwable cause) {
        super(cause);
    }

    public SyncException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
