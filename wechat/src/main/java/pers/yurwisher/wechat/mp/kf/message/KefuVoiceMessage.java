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
 * @description 语音消息
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuVoiceMessage extends KefuMessage {


    private static final long serialVersionUID = -1345021188573488731L;
    private KefuVoice voice ;

    public KefuVoiceMessage() {
        this.voice = new KefuVoice();
        super.setMsgType(WeChatConstant.KefuMsgType.VOICE);
    }

    @Data
    public static class KefuVoice implements Serializable {
        private static final long serialVersionUID = -8742622304331573139L;
        /**
         * 发送的图片/语音/视频/图文消息（点击跳转到图文消息页）的媒体ID
         */
        @JSONField(name = "media_id")
        private String mediaId;

    }


}
