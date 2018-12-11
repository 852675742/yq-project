package pers.yurwisher.wechat.mp.kf.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.mp.kf.KefuMessage;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/07/31 15:15
 * @description 音乐消息
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuMusicMessage extends KefuMessage {


    private static final long serialVersionUID = -2352708389964221019L;
    private KefuMusic music ;

    public KefuMusicMessage() {
        this.music = new KefuMusic();
        super.setMsgType(WeChatConstant.KefuMsgType.MUSIC);
    }

    @Data
    public static class KefuMusic implements Serializable {

        private static final long serialVersionUID = -9032713415267939352L;

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
        @JSONField(name = "thumb_media_id")
        private String thumbMediaId;

        /**
         * 音乐链接
         */
        @JSONField(name = "musicurl")
        private String musicUrl;

        /**
         * 高品质音乐链接，wifi环境优先使用该链接播放音乐
         */
        @JSONField(name = "hqmusicurl")
        private String  hqMusicUrl ;

    }
}
