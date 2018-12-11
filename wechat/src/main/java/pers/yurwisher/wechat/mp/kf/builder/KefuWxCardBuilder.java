package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.mp.kf.message.KefuWxCardMessage;

/**
 * @author yq
 * @date 2018/07/31 16:21
 * @description 客服微信卡卷builder
 * @since V1.0.0
 */
public class KefuWxCardBuilder extends BaseKefuBuilder<KefuWxCardBuilder, KefuWxCardMessage> {

    private String cardId;

    public KefuWxCardBuilder cardId(String cardId) {
        this.cardId = cardId;
        return this;
    }

    @Override
    public KefuWxCardMessage build() {
        KefuWxCardMessage message = new KefuWxCardMessage();
        super.setToUser(message);
        message.getWxCard().setCardId(cardId);
        return message;
    }
}
