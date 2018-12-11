package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.mp.kf.message.KefuVideoMessage;

/**
 * @author yq
 * @date 2018/07/31 15:02
 * @description 客服视频消息builder
 * @since V1.0.0
 */
public class KefuVideoBuilder extends BaseKefuBuilder<KefuVideoBuilder, KefuVideoMessage> {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String mediaId;

    /**
     * 视频消息的标题
     */
    private String title;

    /**
     * 视频消息的描述
     */
    private String description;

    /**
     * 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
     */
    private String thumbMediaId;

    public KefuVideoBuilder mediaId(String mediaId) {
        this.mediaId = mediaId;
        return this ;
    }

    public KefuVideoBuilder title(String title) {
        this.title = title;
        return this ;
    }

    public KefuVideoBuilder description(String description) {
        this.description = description;
        return this ;
    }

    public KefuVideoBuilder thumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
        return this ;
    }

    @Override
    public KefuVideoMessage build() {
        KefuVideoMessage message = new KefuVideoMessage();
        super.setToUser(message);
        message.getVideo().setMediaId(mediaId);
        message.getVideo().setTitle(title);
        message.getVideo().setDescription(description);
        message.getVideo().setThumbMediaId(thumbMediaId);
        return message;
    }
}
