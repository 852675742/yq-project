package pers.yurwisher.syncmanager.support;

/**
 * @author yq
 * @date 2019/01/12 11:30
 * @description 执行异常回调
 * @since V1.0.0
 */
public interface IProcessExceptionHandle<T> {

    /**
     * 异常回调
     * @param messageExecute 待处理消息
     * @param e 异常
     */
    void handle(MessageExecute<T> messageExecute, Throwable e);
}
