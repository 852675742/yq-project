package pers.yurwisher.token.exception;

/**
 * @author yq
 * @date 2018/12/11 09:47
 * @description token异常
 * @since V1.0.0
 */
public class TokenException extends RuntimeException {
    private static final long serialVersionUID = -5803651017009734943L;

    public TokenException() {
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }

    public TokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
