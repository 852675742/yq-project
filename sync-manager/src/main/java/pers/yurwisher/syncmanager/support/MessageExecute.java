package pers.yurwisher.syncmanager.support;

/**
 * @author yq
 * @date 2019/01/12 09:50
 * @description 待处理消息
 * @since V1.0.0
 */
public class MessageExecute<T>{
    /**
     * 待处理消息内容
     */
    private T message;
    /**
     * 执行次数
     */
    private int invokeCount;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public int getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(int invokeCount) {
        this.invokeCount = invokeCount;
    }
}
