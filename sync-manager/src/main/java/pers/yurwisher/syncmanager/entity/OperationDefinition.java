package pers.yurwisher.syncmanager.entity;


import java.util.Date;

public class OperationDefinition {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 接口名称
     */
    private String methodName;

    /**
     * 服务类名称
     */
    private String serviceName;

    /**
     * 创建时间
     */
    private Date dateCreated;

    /**
     * 最后修改时间
     */
    private Date lastUpdated;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 启用/禁用
     */
    private Boolean enabled;

    /**
     * 是否需要发送消息
     */
    private Boolean needSendMessage;

    /**
     * 队列名称
     */
    private String destination;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取接口名称
     *
     * @return method_name - 接口名称
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置接口名称
     *
     * @param methodName 接口名称
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取服务类名称
     *
     * @return service_name - 服务类名称
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * 设置服务类名称
     *
     * @param serviceName 服务类名称
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
     * 获取最后修改时间
     *
     * @return last_updated - 最后修改时间
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastUpdated 最后修改时间
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * 获取创建人名称
     *
     * @return creator_name - 创建人名称
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 设置创建人名称
     *
     * @param creatorName 创建人名称
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 获取接口描述
     *
     * @return description - 接口描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置接口描述
     *
     * @param description 接口描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取启用/禁用
     *
     * @return enabled - 启用/禁用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置启用/禁用
     *
     * @param enabled 启用/禁用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取是否需要发送消息
     *
     * @return need_send_message - 是否需要发送消息
     */
    public Boolean getNeedSendMessage() {
        return needSendMessage;
    }

    /**
     * 设置是否需要发送消息
     *
     * @param needSendMessage 是否需要发送消息
     */
    public void setNeedSendMessage(Boolean needSendMessage) {
        this.needSendMessage = needSendMessage;
    }

    /**
     * 获取队列名称
     *
     * @return destination - 队列名称
     */
    public String getDestination() {
        return destination;
    }

    /**
     * 设置队列名称
     *
     * @param destination 队列名称
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
}