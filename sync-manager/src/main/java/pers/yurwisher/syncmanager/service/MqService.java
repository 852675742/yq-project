package pers.yurwisher.syncmanager.service;

/**
 * @author yq
 * @date 2019/01/15 16:15
 * @description MQ service
 * @since V1.0.0
 */
public interface MqService {

    /**
     *  发送消息
     * @param destinationName 目的地名称
     * @param message 消息
     */
    void send(String destinationName,final String message);

}
