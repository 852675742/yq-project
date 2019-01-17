package pers.yurwisher.syncmanager.entity;

import java.util.Date;

public class MessageRecord {
    /**
     * 主键
     */
    private Long id;

    /**
     * 消息号唯一
     */
    private String messageNo;

    /**
     * 消息发送端名称
     */
    private String senderName;

    /**
     * 消息状态
     */
    private MessageStatusEnum status;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 本次操作ID
     */
    private Integer operationId;

    /**
     * 关联单据号
     */
    private String docNo;

    /**
     * 链条号,用于标识一组操作
     */
    private String chainNo;

    /**
     * 创建时间
     */
    private Date dateCreated;

    /**
     * 最后处理时间
     */
    private Date lastUpdated;

    /**
     * 消息详情,参数
     */
    private String payload;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取消息号唯一
     *
     * @return message_no - 消息号唯一
     */
    public String getMessageNo() {
        return messageNo;
    }

    /**
     * 设置消息号唯一
     *
     * @param messageNo 消息号唯一
     */
    public void setMessageNo(String messageNo) {
        this.messageNo = messageNo;
    }

    /**
     * 获取消息发送端名称
     *
     * @return sender_name - 消息发送端名称
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * 设置消息发送端名称
     *
     * @param senderName 消息发送端名称
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * 获取消息状态
     *
     * @return status - 消息状态
     */
    public MessageStatusEnum getStatus() {
        return status;
    }

    /**
     * 设置消息状态
     *
     * @param status 消息状态
     */
    public void setStatus(MessageStatusEnum status) {
        this.status = status;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取本次操作ID
     *
     * @return operation_id - 本次操作ID
     */
    public Integer getOperationId() {
        return operationId;
    }

    /**
     * 设置本次操作ID
     *
     * @param operationId 本次操作ID
     */
    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取关联单据号
     *
     * @return doc_no - 关联单据号
     */
    public String getDocNo() {
        return docNo;
    }

    /**
     * 设置关联单据号
     *
     * @param docNo 关联单据号
     */
    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    /**
     * 获取链条号,用于标识一组操作
     *
     * @return chain_no - 链条号,用于标识一组操作
     */
    public String getChainNo() {
        return chainNo;
    }

    /**
     * 设置链条号,用于标识一组操作
     *
     * @param chainNo 链条号,用于标识一组操作
     */
    public void setChainNo(String chainNo) {
        this.chainNo = chainNo;
    }

    /**
     * 获取创建时间
     *
     * @return date_created - 创建时间
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * 设置创建时间
     *
     * @param dateCreated 创建时间
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * 获取最后处理时间
     *
     * @return last_updated - 最后处理时间
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * 设置最后处理时间
     *
     * @param lastUpdated 最后处理时间
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * 获取消息详情,参数
     *
     * @return payload - 消息详情,参数
     */
    public String getPayload() {
        return payload;
    }

    /**
     * 设置消息详情,参数
     *
     * @param payload 消息详情,参数
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}