package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.mp.kf.message.KefuVoiceMessage;

/**
 * @author yq
 * @date 2018/07/31 15:02
 * @description 客服语音消息builder
 * @since V1.0.0
 */
public class KefuVoiceBuilder extends BaseKefuBuilder<KefuVoiceBuilder, KefuVoiceMessage> {

    /**
     * 发送的图片/语音/视频/图文消息（点击跳转到图文消息页）的媒体ID
     */
    private String mediaId;

    public KefuVoiceBuilder mediaId(String mediaId){
        this.mediaId = mediaId;
        return this ;
    }

    @Override
    public KefuVoiceMessage build() {
        KefuVoiceMessage message = new KefuVoiceMessage();
        super.setToUser(message);
        message.getVoice().setMediaId(mediaId);
        return message;
    }
}
