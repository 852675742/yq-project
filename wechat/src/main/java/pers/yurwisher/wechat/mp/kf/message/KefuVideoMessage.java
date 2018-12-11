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
 * @description 视频消息
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuVideoMessage extends KefuMessage {


    private static final long serialVersionUID = -1088752451245510017L;
    private KefuVideo video ;

    public KefuVideoMessage() {
        this.video = new KefuVideo();
        super.setMsgType(WeChatConstant.KefuMsgType.VIDEO);
    }

    @Data
    public static class KefuVideo implements Serializable {
        private static final long serialVersionUID = -3425986492750700414L;

        /**
         * 通过素材管理中的接口上传多媒体文件，得到的id
         */
        @JSONField(name = "media_id")
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
        @JSONField(name = "thumb_media_id")
        private String thumbMediaId;

    }

}
