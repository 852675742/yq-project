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
 * @description 图片消息
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuImageMessage extends KefuMessage {

    private static final long serialVersionUID = 5553950878578674021L;

    private KefuImage image ;

    public KefuImageMessage() {
        this.image = new KefuImage();
        super.setMsgType(WeChatConstant.KefuMsgType.IMAGE);
    }

    @Data
    public static class KefuImage implements Serializable {
        private static final long serialVersionUID = 6730861925023299031L;
        /**
         * 发送的图片/语音/视频/图文消息（点击跳转到图文消息页）的媒体ID
         */
        @JSONField(name = "media_id")
        private String mediaId;
    }
}
