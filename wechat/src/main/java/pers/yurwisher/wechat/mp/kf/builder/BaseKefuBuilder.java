package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.mp.kf.KefuMessage;

/**
 * @author yq
 * @date 2018/07/31 14:57
 * @description 客服回复消息builder
 * @since V1.0.0
 */
public abstract class BaseKefuBuilder<BuilderType,Message> {

    private String toUser;

    /**
     * 不同消息类型对应的独立报文构建
     *
     * @return Message
     */
    public abstract Message build();

    @SuppressWarnings("unchecked")
    public BuilderType toUser(String toUser) {
        this.toUser = toUser;
        return (BuilderType)this ;
    }

    public void setToUser(KefuMessage message){
        message.setToUser(toUser);
    }
}
