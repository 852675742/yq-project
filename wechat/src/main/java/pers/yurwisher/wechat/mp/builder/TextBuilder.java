package pers.yurwisher.wechat.mp.builder;


import pers.yurwisher.wechat.mp.message.WxTextMessage;

/**
 * 文本消息builder
 *
 * @author yq
 */
public final class TextBuilder extends BaseBuilder<TextBuilder, WxTextMessage> {

    private String content;

    public TextBuilder content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public WxTextMessage build() {
        WxTextMessage m = new WxTextMessage();
        setCommon(m);
        m.setContent(this.content);
        return m;
    }
}
