package pers.yurwisher.syncmanager.support.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.entity.MessageStatusEnum;
import pers.yurwisher.syncmanager.entity.OperationDefinition;
import pers.yurwisher.syncmanager.exception.SyncException;
import pers.yurwisher.syncmanager.service.MessageRecordService;
import pers.yurwisher.syncmanager.service.MqService;
import pers.yurwisher.syncmanager.service.OperationDefinitionService;
import pers.yurwisher.syncmanager.support.IProcessService;
import pers.yurwisher.syncmanager.support.MessageExecute;

/**
 * @author yq
 * @date 2019/01/15 15:30
 * @description 执行service
 * @since V1.0.0
 */
@Service
public class ProcessServiceImpl implements IProcessService<MessageRecord> {

    private static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);

    private MessageRecordService messageRecordService ;
    private OperationDefinitionService operationDefinitionService ;
    private MqService mqService;
    private Integer retryNumber;

    public ProcessServiceImpl(MessageRecordService messageRecordService, OperationDefinitionService operationDefinitionService, MqService mqService, Integer retryNumber) {
        this.messageRecordService = messageRecordService;
        this.operationDefinitionService = operationDefinitionService;
        this.mqService = mqService;
        this.retryNumber = retryNumber;
    }

    @Override
    public void process(MessageExecute<MessageRecord> record){
        MessageRecord message = record.getMessage();
        //日志存在 且非成功才执行
        if(message != null && MessageStatusEnum.SUCCEED != message.getStatus()){
            logger.info("准备处理日志,唯一码:{}",message.getMessageNo());
            //本次要执行的接口
            OperationDefinition definition = operationDefinitionService.getOperationDefinition(message.getOperationId());
            if(definition != null){
                //本次操作参数
                JSONObject params = JSON.parseObject(message.getPayload());
                Object returnData;
                try {
                    //执行接口 反射执行接口后的返回值
                    returnData = operationDefinitionService.invoke(message,definition,params);
                } catch (Throwable throwable) {
                    String cause = throwable.getCause() != null ? throwable.getCause().getMessage() : throwable.getMessage();
                    //保存日志,更新状态
                    messageRecordService.invokeExceptionSave(message,cause,retryNumber);
                    throw new SyncException(cause);
                }
                //事务提交后 确认是否需要发消息
                if(mqService != null && definition.getNeedSendMessage() && returnData != null){
                    JSONObject jsonObject  =  (JSONObject) returnData;
                    //chainNo 传递
                    jsonObject.put("chainNo",message.getChainNo());
                    mqService.send(definition.getDestination(),jsonObject.toJSONString());
                }
            }
        }else {
            throw new SyncException("已经成功处理,无需再次触发!");
        }
    }

}
