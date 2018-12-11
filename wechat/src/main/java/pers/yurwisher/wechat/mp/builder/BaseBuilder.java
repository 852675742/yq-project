package pers.yurwisher.wechat.mp.builder;


import pers.yurwisher.wechat.mp.message.WxMessage;

/**
 * 消息构造
 * @param <BuilderType> 构造类型
 * @param <ValueType> 消息
 * @author yq
 */
public abstract class BaseBuilder<BuilderType, ValueType> {

    protected String toUserName;

    protected String fromUserName;

    @SuppressWarnings("unchecked")
    public BuilderType toUser(String toUser) {
        this.toUserName = toUser;
        return (BuilderType) this;
    }

    @SuppressWarnings("unchecked")
    public BuilderType fromUser(String fromUser) {
        this.fromUserName = fromUser;
        return (BuilderType) this;
    }

    /**
     * 不同消息类型对应的独立报文构建
     *
     * @return ValueType
     */
    public abstract ValueType build();

    /**
     * 通用设置
     * @param m 输出消息
     */
    public void setCommon(WxMessage m) {
        m.setToUserName(this.toUserName);
        m.setFromUserName(this.fromUserName);
        m.setCreateTime(System.currentTimeMillis() / 1000L);
    }


}
