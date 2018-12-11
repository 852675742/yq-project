package pers.yurwisher.wechat.mp.api;


import pers.yurwisher.wechat.mp.in.WxMpXmlMessage;

/**
 * @author yq
 * @date 2018/07/18 10:53
 * @description 复杂消息匹配
 * @since V1.0.0
 */
public interface WxMessageMatcher {

    /**
     * 消息是否匹配某种模式
     *
     * @param message xml消息
     * @return boolean
     */
    boolean match(WxMpXmlMessage message);
}
