package pers.yurwisher.wechat.mp.builder;


import pers.yurwisher.wechat.mp.message.WxVoiceMessage;

/**
 * 语音消息builder
 *
 * @author yq
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder, WxVoiceMessage> {

  private String mediaId;

  public VoiceBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxVoiceMessage build() {
    WxVoiceMessage m = new WxVoiceMessage();
    setCommon(m);
    m.getVoice().setMediaId(this.mediaId);
    return m;
  }

}
