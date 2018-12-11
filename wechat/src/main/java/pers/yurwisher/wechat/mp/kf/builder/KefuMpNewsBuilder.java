package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.mp.kf.message.KefuMpNewsMessage;

/**
 * @author yq
 * @date 2018/07/31 16:15
 * @description 发送图文消息（点击跳转到图文消息页面）
 * @since V1.0.0
 */
public class KefuMpNewsBuilder extends BaseKefuBuilder<KefuMpNewsBuilder, KefuMpNewsMessage> {

    /**
     * 发送的图片/语音/视频/图文消息（点击跳转到图文消息页）的媒体ID
     */
    private String mediaId;

    public KefuMpNewsBuilder mediaId(String mediaId){
        this.mediaId = mediaId;
        return this ;
    }

    @Override
    public KefuMpNewsMessage build() {
        KefuMpNewsMessage message = new KefuMpNewsMessage();
        super.setToUser(message);
        message.getMpNews().setMediaId(mediaId);
        return message;
    }
}
