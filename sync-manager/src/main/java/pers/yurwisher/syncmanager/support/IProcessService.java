package pers.yurwisher.syncmanager.support;

/**
 * @author yq
 * @date 2019/01/09 15:57
 * @description 消息执行对应的方法
 * @since V1.0.0
 */
public interface IProcessService<T> {

    /**
     * 执行
     * @param record 待处理消息
     * @throws Exception 异常
     */
    void process(MessageExecute<T> record) throws Exception;


}
