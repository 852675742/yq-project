package pers.yurwisher.wechat.mp.api;


import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.mp.in.WxMpXmlMessage;
import pers.yurwisher.wechat.mp.message.WxMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 消息路由规则
 */
public class WxMessageRouterRule {

    /**
     * 消息路由保证单例
     */
    private final WxMessageRouter routerBuilder;

    /**
     * 是否异步执行
     */
    private boolean async = true;

    /**
     * 用户
     */
    private String fromUser;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 事件类型
     */
    private String event;

    /**
     * 事件key
     */
    private String eventKey;

    /**
     * 事件key 正则
     */
    private String eventKeyRegex;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息内容 正则
     */
    private String contentRegex;

    /**
     * 用户自定义的消息匹配规则
     */
    private WxMessageMatcher matcher;

    /**
     * 是否允许继续往下匹配
     */
    private boolean reEnter = false;

    /**
     * 匹配的消息处理器
     */
    private List<WxMessageHandler> handlers = new ArrayList<>();

    public WxMessageRouterRule(WxMessageRouter routerBuilder) {
        this.routerBuilder = routerBuilder;
    }

    /**
     * 设置是否异步执行，默认是true
     */
    public WxMessageRouterRule async(boolean async) {
        this.async = async;
        return this;
    }

    /**
     * 如果msgType等于某值
     */
    public WxMessageRouterRule msgType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    /**
     * 如果event等于某值
     */
    public WxMessageRouterRule event(String event) {
        this.event = event;
        return this;
    }

    /**
     * 如果eventKey等于某值
     */
    public WxMessageRouterRule eventKey(String eventKey) {
        this.eventKey = eventKey;
        return this;
    }

    /**
     * 如果eventKey匹配该正则表达式
     */
    public WxMessageRouterRule eventKeyRegex(String regex) {
        this.eventKeyRegex = regex;
        return this;
    }

    /**
     * 如果content等于某值
     */
    public WxMessageRouterRule content(String content) {
        this.content = content;
        return this;
    }

    /**
     * 如果content匹配该正则表达式
     */
    public WxMessageRouterRule contentRegex(String contentRegex) {
        this.contentRegex = contentRegex;
        return this;
    }

    /**
     * 如果fromUser等于某值
     */
    public WxMessageRouterRule fromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    /**
     * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候
     */
    public WxMessageRouterRule matcher(WxMessageMatcher matcher) {
        this.matcher = matcher;
        return this;
    }

    /**
     * 设置微信消息处理器
     */
    public WxMessageRouterRule handler(WxMessageHandler... otherHandlers) {
        if (otherHandlers != null && otherHandlers.length > 0) {
            Collections.addAll(this.handlers, otherHandlers);
        }
        return this;
    }

    /**
     * 将微信自定义的事件修正为不区分大小写,
     * 比如框架定义的事件常量为click，但微信传递过来的却是CLICK
     * 消息匹配
     */
    protected boolean test(WxMpXmlMessage wxMessage) {
        return
                (this.fromUser == null || this.fromUser.equals(wxMessage.getFromUser()))
                        &&
                        (this.msgType == null || this.msgType.equalsIgnoreCase(wxMessage.getMsgType()))
                        &&
                        (this.event == null || this.event.equalsIgnoreCase(wxMessage.getEvent()))
                        &&
                        (this.eventKey == null || this.eventKey.equalsIgnoreCase(wxMessage.getEventKey()))
                        &&
                        (this.eventKeyRegex == null || Pattern.matches(this.eventKeyRegex, Utils.null2EmptyWithTrimNew(wxMessage.getEventKey())))
                        &&
                        (this.content == null || this.content.equals(Utils.null2EmptyWithTrimNew(wxMessage.getContent())))
                        &&
                        (this.contentRegex == null || Pattern.matches(this.contentRegex, Utils.null2EmptyWithTrimNew(wxMessage.getContent())))
                        &&
                        (this.matcher == null || this.matcher.match(wxMessage))
                ;
    }

    /**
     * 处理微信推送过来的消息
     */
    protected WxMessage service(WxMpXmlMessage wxMessage) {
        // 交给handler处理
        WxMessage res = null;
        if(this.handlers.size() != 0){
            //多个handler返回最后一个的结果
            res = this.handlers.get(this.handlers.size() - 1).handle(wxMessage);
        }
        return res;
    }

    /**
     * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
     */
    public WxMessageRouter end() {
        this.routerBuilder.getRules().add(this);
        return this.routerBuilder;
    }

    /**
     * 规则结束，但是消息还会进入其他规则
     */
    public WxMessageRouter next() {
        this.reEnter = true;
        return end();
    }

    public boolean isAsync() {
        return this.async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getFromUser() {
        return this.fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return this.event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return this.eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentRegex() {
        return this.contentRegex;
    }

    public void setContentRegex(String rContent) {
        this.contentRegex = rContent;
    }

    public WxMessageMatcher getMatcher() {
        return this.matcher;
    }

    public void setMatcher(WxMessageMatcher matcher) {
        this.matcher = matcher;
    }

    public boolean isReEnter() {
        return this.reEnter;
    }

    public void setReEnter(boolean reEnter) {
        this.reEnter = reEnter;
    }

    public List<WxMessageHandler> getHandlers() {
        return this.handlers;
    }

    public void setHandlers(List<WxMessageHandler> handlers) {
        this.handlers = handlers;
    }

}
