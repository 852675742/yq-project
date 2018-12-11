package pers.yurwisher.wechat.exception;

/**
 * @author yq
 * @date 2018/07/06 14:06
 * @description 微信异常
 * @since V1.0.0
 */
public class WeChatException extends RuntimeException {

    public WeChatException() {
    }

    public WeChatException(String message) {
        super(message);
    }

    public WeChatException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeChatException(Throwable cause) {
        super(cause);
    }

    public WeChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
