package pers.yurwisher.wechat.mp.api;


import pers.yurwisher.wechat.mp.in.WxMpXmlMessage;
import pers.yurwisher.wechat.mp.message.WxMessage;

/**
 * @author yq
 * @date 2018/07/18 10:55
 * @description 消息处理器接口
 * @since V1.0.0
 */
public interface WxMessageHandler {

    /**
     * 消息处理
     * @param wxMpXmlMessage 微信服务器返回的消息
     * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
     */
    WxMessage handle(WxMpXmlMessage wxMpXmlMessage);
}
