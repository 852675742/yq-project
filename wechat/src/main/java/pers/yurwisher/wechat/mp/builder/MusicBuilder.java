package pers.yurwisher.wechat.mp.builder;


import pers.yurwisher.wechat.mp.message.WxMusicMessage;

/**
 * 音乐消息builder
 *
 * @author chanjarster
 */
public final class MusicBuilder extends BaseBuilder<MusicBuilder, WxMusicMessage> {

  private String title;
  private String description;
  private String hqMusicUrl;
  private String musicUrl;
  private String thumbMediaId;

  public MusicBuilder title(String title) {
    this.title = title;
    return this;
  }

  public MusicBuilder description(String description) {
    this.description = description;
    return this;
  }

  public MusicBuilder hqMusicUrl(String hqMusicUrl) {
    this.hqMusicUrl = hqMusicUrl;
    return this;
  }

  public MusicBuilder musicUrl(String musicUrl) {
    this.musicUrl = musicUrl;
    return this;
  }

  public MusicBuilder thumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
    return this;
  }

  @Override
  public WxMusicMessage build() {
    WxMusicMessage m = new WxMusicMessage();
    setCommon(m);
    m.getMusic().setTitle(this.title);
    m.getMusic().setDescription(this.description);
    m.getMusic().setHqMusicUrl(this.hqMusicUrl);
    m.getMusic().setMusicUrl(this.musicUrl);
    m.getMusic().setThumbMediaId(this.thumbMediaId);
    return m;
  }

}
