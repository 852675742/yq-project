package pers.yurwisher.wechat.mp.kf.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.mp.kf.KefuMessage;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/07/31 16:17
 * @description 发送卡券 ,客服消息接口投放卡券仅支持非自定义Code码和导入code模式的卡券的卡券
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuWxCardMessage extends KefuMessage {

    private static final long serialVersionUID = 6321961923923035453L;

    @JSONField(name = "wxcard")
    private KefuWxCard wxCard ;

    public KefuWxCardMessage() {
        this.wxCard = new KefuWxCard();
        super.setMsgType(WeChatConstant.KefuMsgType.WXCARD);
    }

    @Data
    public static class KefuWxCard implements Serializable {
        private static final long serialVersionUID = -8742622304331573139L;
        /**
         * 卡卷ID
         */
        @JSONField(name = "card_id")
        private String cardId;
    }

}
