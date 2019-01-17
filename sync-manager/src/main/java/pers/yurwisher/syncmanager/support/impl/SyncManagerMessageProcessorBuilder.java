package pers.yurwisher.syncmanager.support.impl;

import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.service.MessageRecordService;
import pers.yurwisher.syncmanager.service.MqService;
import pers.yurwisher.syncmanager.service.OperationDefinitionService;
import pers.yurwisher.syncmanager.support.IProcessExceptionHandle;

public class SyncManagerMessageProcessorBuilder {
    private Integer processThreadNumber;
    private String processThreadNamePrefix;
    private Integer queueSize;
    private Integer retryNumber;
    private IProcessExceptionHandle<MessageRecord> processExceptionHandle;
    private MessageRecordService messageRecordService;
    private OperationDefinitionService operationDefinitionService;
    private MqService mqService;

    public SyncManagerMessageProcessorBuilder setProcessThreadNumber(Integer processThreadNumber) {
        this.processThreadNumber = processThreadNumber;
        return this;
    }

    public SyncManagerMessageProcessorBuilder setProcessThreadNamePrefix(String processThreadNamePrefix) {
        this.processThreadNamePrefix = processThreadNamePrefix;
        return this;
    }

    public SyncManagerMessageProcessorBuilder setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
        return this;
    }

    public SyncManagerMessageProcessorBuilder setRetryNumber(Integer retryNumber) {
        this.retryNumber = retryNumber;
        return this;
    }

    public SyncManagerMessageProcessorBuilder setProcessExceptionHandle(IProcessExceptionHandle<MessageRecord> processExceptionHandle) {
        this.processExceptionHandle = processExceptionHandle;
        return this;
    }

    public SyncManagerMessageProcessorBuilder setMessageRecordService(MessageRecordService messageRecordService) {
        this.messageRecordService = messageRecordService;
        return this;
    }

    public SyncManagerMessageProcessorBuilder setOperationDefinitionService(OperationDefinitionService operationDefinitionService) {
        this.operationDefinitionService = operationDefinitionService;
        return this;
    }

    public SyncManagerMessageProcessorBuilder setMqService(MqService mqService) {
        this.mqService = mqService;
        return this;
    }

    public SyncManagerMessageProcessor build() {
        return new SyncManagerMessageProcessor(processThreadNumber, processThreadNamePrefix, queueSize, retryNumber, processExceptionHandle, messageRecordService, operationDefinitionService, mqService);
    }

}