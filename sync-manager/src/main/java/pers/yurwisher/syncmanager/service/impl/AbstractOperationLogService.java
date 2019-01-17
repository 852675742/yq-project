package pers.yurwisher.syncmanager.service.impl;

import org.springframework.transaction.annotation.Transactional;
import pers.yurwisher.syncmanager.entity.MessageStatusEnum;
import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.entity.OperationLog;
import pers.yurwisher.syncmanager.service.OperationLogService;

import java.util.Date;

/**
 * @author yq
 * @date 2019/01/15 17:08
 * @description 操作日志
 * @since V1.0.0
 */
public abstract class AbstractOperationLogService implements OperationLogService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOperationLog(MessageRecord message, MessageStatusEnum statusEnum, String detail) {
        OperationLog log = createLog(message, statusEnum, detail);
        saveOperationLog(log);
    }

    private OperationLog createLog(MessageRecord message, MessageStatusEnum statusEnum, String detail){
        OperationLog log = new OperationLog();
        log.setDateCreated(new Date());
        log.setDetail(detail);
        log.setOperator(getCurrentUserName());
        log.setRecordId(message.getId());
        log.setStatus(statusEnum.name());
        return log;
    }
}
