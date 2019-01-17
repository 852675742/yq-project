package pers.yurwisher.syncmanager.support.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.service.MessageRecordService;
import pers.yurwisher.syncmanager.service.MqService;
import pers.yurwisher.syncmanager.service.OperationDefinitionService;
import pers.yurwisher.syncmanager.support.IMessageProcessor;
import pers.yurwisher.syncmanager.support.IProcessExceptionHandle;
import pers.yurwisher.syncmanager.support.IProcessService;
import pers.yurwisher.syncmanager.support.MessageExecute;
import pers.yurwisher.syncmanager.support.Processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author yq
 * @date 2019/01/12 09:57
 * @description 消息预执行器
 * @since V1.0.0
 */
public class SyncManagerMessageProcessor implements IMessageProcessor<MessageRecord> {

    private static final Logger logger = LoggerFactory.getLogger(SyncManagerMessageProcessor.class);

    /**
     * 执行线程数
     */
    private Integer processThreadNumber ;
    /**
     * 执行线程名称前缀
     */
    private String processThreadNamePrefix ;
    /**
     * 队列大小
     */
    private Integer queueSize;
    /**
     * 执行器
     */
    private List<Processor<MessageRecord>> processors ;
    /**
     * 执行的方法
     */
    private IProcessService<MessageRecord> processService;
    /**
     * 重试次数
     */
    private Integer retryNumber;
    /**
     * 消息处理次数超过阀值后的处理
     */
    private IProcessExceptionHandle<MessageRecord> processExceptionHandle;

    private MessageRecordService messageRecordService ;

    public SyncManagerMessageProcessor(Integer processThreadNumber,
                                       String processThreadNamePrefix,
                                       Integer queueSize,
                                       Integer retryNumber,
                                       IProcessExceptionHandle<MessageRecord> processExceptionHandle,
                                       MessageRecordService messageRecordService,
                                       OperationDefinitionService operationDefinitionService,
                                       MqService mqService) {
        this.processThreadNumber = processThreadNumber;
        this.processThreadNamePrefix = processThreadNamePrefix;
        this.queueSize = queueSize;
        this.retryNumber = retryNumber;
        this.processExceptionHandle = processExceptionHandle;
        this.messageRecordService = messageRecordService;
        this.processService = new ProcessServiceImpl(messageRecordService,operationDefinitionService,mqService,retryNumber);
        this.init();
    }

    @Override
    public boolean put(MessageRecord message) {
        MessageExecute<MessageRecord> execute = new MessageExecute<>();
        execute.setMessage(message);
        Processor<MessageRecord> processor = processors.get(calculateProcessor(execute));
        return processor.put(execute);
    }

    private void init() {
        logger.info("初始化消息执行器数量:{}",processThreadNumber);
        processors = new ArrayList<>(processThreadNumber);
        //初始化消息执行器
        for (int i = 0; i < processThreadNumber; i++) {
            processors.add(new Processor<>(i,queueSize,processThreadNamePrefix,retryNumber,processService,processExceptionHandle));
        }
    }

    @Override
    public Integer calculateProcessor(MessageExecute<MessageRecord> messageExecute) {
        int processNumber = ThreadLocalRandom.current().nextInt(processThreadNumber);
        logger.info("{} 随机置入执行器:{}",messageExecute.getMessage().getMessageNo(),processNumber);
        return processNumber;
    }

    @Override
    public void afterListeningDo(String message) {
        if(!StringUtils.isEmpty(message)){
            MessageRecord  messageRecord = JSON.parseObject(message,MessageRecord.class);
            Date now = new Date();
            messageRecord.setDateCreated(now);
            messageRecord.setLastUpdated(now);
            //save record
            messageRecord = messageRecordService.saveMessageRecord(messageRecord);
            //put into processor ,then process
            put(messageRecord);
        }
    }

    @Override
    public void trigger(MessageRecord  messageRecord) {
        if(messageRecord != null){
            //put into processor ,then process
            put(messageRecord);
        }
    }
}
