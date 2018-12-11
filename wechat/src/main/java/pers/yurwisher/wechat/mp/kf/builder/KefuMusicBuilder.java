package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.mp.kf.message.KefuMusicMessage;

/**
 * @author yq
 * @date 2018/07/31 15:15
 * @description 音乐消息builder
 * @since V1.0.0
 */
public class KefuMusicBuilder extends BaseKefuBuilder<KefuMusicBuilder, KefuMusicMessage> {

    /**
     * 音乐消息的标题
     */
    private String title;

    /**
     * 音乐消息的描述
     */
    private String description;

    /**
     * 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
     */
    private String thumbMediaId;

    /**
     * 音乐链接
     */
    private String musicUrl;

    /**
     * 高品质音乐链接，wifi环境优先使用该链接播放音乐
     */
    private String  hqMusicUrl ;


    public KefuMusicBuilder title(String title) {
        this.title = title;
        return this ;
    }

    public KefuMusicBuilder description(String description) {
        this.description = description;
        return this ;
    }

    public KefuMusicBuilder thumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
        return this ;
    }

    public KefuMusicBuilder musicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
        return this ;
    }

    public KefuMusicBuilder hqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
        return this ;
    }

    @Override
    public KefuMusicMessage build() {
        KefuMusicMessage message = new KefuMusicMessage();
        super.setToUser(message);
        message.getMusic().setTitle(title);
        message.getMusic().setDescription(description);
        message.getMusic().setThumbMediaId(thumbMediaId);
        message.getMusic().setHqMusicUrl(hqMusicUrl);
        message.getMusic().setMusicUrl(musicUrl);
        return message;
    }
}
