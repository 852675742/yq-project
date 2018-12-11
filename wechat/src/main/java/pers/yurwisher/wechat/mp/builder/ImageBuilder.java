package pers.yurwisher.wechat.mp.builder;


import pers.yurwisher.wechat.mp.message.WxImageMessage;

/**
 * 图片消息builder
 *
 * @author chanjarster
 */
public final class ImageBuilder extends BaseBuilder<ImageBuilder, WxImageMessage> {

    private String mediaId;

    public ImageBuilder mediaId(String media_id) {
        this.mediaId = media_id;
        return this;
    }

    @Override
    public WxImageMessage build() {
        WxImageMessage m = new WxImageMessage();
        setCommon(m);
        m.getVideo().setMediaId(this.mediaId);
        return m;
    }

}
