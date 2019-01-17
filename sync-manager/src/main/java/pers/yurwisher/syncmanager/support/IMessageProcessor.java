package pers.yurwisher.syncmanager.support;



/**
 * @author yq
 * @date 2019/01/12 09:48
 * @description 消息执行器接口
 * @since V1.0.0
 */
public interface IMessageProcessor<T> {

    /**
     * 置入待处理消息
     *
     * @param message 待处理消息
     * @return 是否成功
     */
    boolean put(T message);

    /**
     * 根据消息 计算得出处理此消息的执行器
     * @param messageExecute 待处理消息
     * @return 执行器编号
     */
    Integer calculateProcessor(MessageExecute<T> messageExecute);

    /**
     * 监听到消息后执行
     * @param message 监听到的消息
     */
    void afterListeningDo(final String message);


    /**
     * 手动再次执行
     * @param messageRecord 待处理消息
     */
    void trigger(T messageRecord);


}
