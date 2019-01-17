package pers.yurwisher.syncmanager.service;

import pers.yurwisher.syncmanager.entity.MessageStatusEnum;
import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.entity.OperationLog;

import java.util.List;

/**
 * @author yq
 * @date 2019/01/15 17:07
 * @description 操作日志
 * @since V1.0.0
 */
public interface OperationLogService {

    /**
     * 消息已异常次数
     * @param messageRecordId 消息ID
     * @return  已异常次数
     */
    Integer exceptionTimes(Long messageRecordId);

    /**
     * 保存操作日志
     * @param message 待处理消息
     * @param statusEnum 消息状态
     * @param detail 执行详情
     */
    void saveOperationLog(MessageRecord message, MessageStatusEnum statusEnum, String detail);

    /**
     * 保存操作日志
     * @param log 日志
     */
    void saveOperationLog(OperationLog log);

    /**
     * 当前用户名
     * @return String
     */
    String getCurrentUserName();
}
