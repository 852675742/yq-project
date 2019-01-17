package pers.yurwisher.syncmanager.service;

import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.entity.MessageStatusEnum;

/**
 * @author yq
 * @date 2019/01/15 17:03
 * @description 待处理消息
 * @since V1.0.0
 */
public interface MessageRecordService {

    /**
     * 保存消息
     * @param messageRecord 待处理消息
     * @return messageRecord
     */
    MessageRecord saveMessageRecord(MessageRecord messageRecord);

    /**
     * 更新待处理消息状态
     * @param id 消息ID
     * @param statusEnum 状态
     */
    void updateStatus(Long id, MessageStatusEnum statusEnum);

    /**
     * 执行异常时保存 日志及更新待处理消息为异常/错误,单独起事务
     * @param message 待处理消息
     * @param cause 异常原因
     * @param thresholdValue 异常转错误阀值
     */
    void invokeExceptionSave(MessageRecord message,String cause,Integer thresholdValue);

    /**
     * 获取待处理消息
     * @param id 主键ID
     * @return MessageRecord
     */
    MessageRecord getMessageRecord(Long id);

}
