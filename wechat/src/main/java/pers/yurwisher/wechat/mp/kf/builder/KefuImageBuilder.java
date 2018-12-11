package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.mp.kf.message.KefuImageMessage;

/**
 * @author yq
 * @date 2018/07/31 15:02
 * @description 客服图片消息builder
 * @since V1.0.0
 */
public class KefuImageBuilder extends BaseKefuBuilder<KefuImageBuilder, KefuImageMessage> {

    /**
     * 发送的图片/语音/视频/图文消息（点击跳转到图文消息页）的媒体ID
     */
    private String mediaId;

    public KefuImageBuilder mediaId(String mediaId){
        this.mediaId = mediaId;
        return this ;
    }

    @Override
    public KefuImageMessage build() {
        KefuImageMessage message = new KefuImageMessage();
        super.setToUser(message);
        message.getImage().setMediaId(mediaId);
        return message;
    }
}
