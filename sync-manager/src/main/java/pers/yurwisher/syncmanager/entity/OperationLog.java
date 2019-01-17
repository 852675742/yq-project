package pers.yurwisher.syncmanager.entity;

import java.util.Date;

public class OperationLog {
    private Long id;

    /**
     * 待处理消息ID
     */
    private Long recordId;

    /**
     * 创建日期
     */
    private Date dateCreated;

    /**
     * 操作人名称
     */
    private String operator;

    /**
     * 执行本次操作后状态名称
     */
    private String status;

    /**
     * 处理详情
     */
    private String detail;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取待处理消息ID
     *
     * @return record_id - 待处理消息ID
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * 设置待处理消息ID
     *
     * @param recordId 待处理消息ID
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取创建日期
     *
     * @return date_created - 创建日期
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * 设置创建日期
     *
     * @param dateCreated 创建日期
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * 获取操作人名称
     *
     * @return operator - 操作人名称
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人名称
     *
     * @param operator 操作人名称
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取执行本次操作后状态名称
     *
     * @return status - 执行本次操作后状态名称
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置执行本次操作后状态名称
     *
     * @param status 执行本次操作后状态名称
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取处理详情
     *
     * @return detail - 处理详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置处理详情
     *
     * @param detail 处理详情
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

}