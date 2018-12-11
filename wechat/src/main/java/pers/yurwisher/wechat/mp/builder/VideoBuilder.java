package pers.yurwisher.wechat.mp.builder;


import pers.yurwisher.wechat.mp.message.WxVideoMessage;

/**
 * 视频消息builder
 * @author chanjarster
 */
public final class VideoBuilder extends BaseBuilder<VideoBuilder, WxVideoMessage> {

  private String mediaId;
  private String title;
  private String description;

  public VideoBuilder title(String title) {
    this.title = title;
    return this;
  }

  public VideoBuilder description(String description) {
    this.description = description;
    return this;
  }

  public VideoBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxVideoMessage build() {
    WxVideoMessage m = new WxVideoMessage();
    setCommon(m);
    m.getVideo().setTitle(this.title);
    m.getVideo().setDescription(this.description);
    m.getVideo().setMediaId(this.mediaId);
    return m;
  }

}
