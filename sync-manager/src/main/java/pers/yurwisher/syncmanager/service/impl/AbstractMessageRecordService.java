package pers.yurwisher.syncmanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.entity.MessageStatusEnum;
import pers.yurwisher.syncmanager.service.MessageRecordService;
import pers.yurwisher.syncmanager.service.OperationLogService;

/**
 * @author yq
 * @date 2019/01/15 17:03
 * @description 待处理消息
 * @since V1.0.0
 */
public abstract class AbstractMessageRecordService implements MessageRecordService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractMessageRecordService.class);

    private OperationLogService operationLogService;

    public AbstractMessageRecordService(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invokeExceptionSave(MessageRecord message, String cause,Integer thresholdValue) {
        MessageStatusEnum messageStatus = MessageStatusEnum.EXCEPTION;
        //日志已异常次数
        Integer exceptionTimes = operationLogService.exceptionTimes(message.getId());
        //超过阀值 将处理日志设置为异常
        if(exceptionTimes >= thresholdValue - 1){
            logger.info("消息:{},转为错误",message.getMessageNo(),thresholdValue);
            messageStatus = MessageStatusEnum.ERROR ;
        }
        //保存系统日志
        operationLogService.saveOperationLog(message,messageStatus,cause);
        //更新消息状态
        updateStatus(message.getId(),messageStatus);
    }

}
