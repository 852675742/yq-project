package pers.yurwisher.wechat.mp.kf.message;


import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.mp.kf.KefuMessage;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/07/31 13:53
 * @description 客服文本消息
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuTextMessage extends KefuMessage {

    private static final long serialVersionUID = 284389615572377989L;

    private KefuText text ;

    public KefuTextMessage() {
        this.text = new KefuText();
        super.setMsgType(WeChatConstant.KefuMsgType.TEXT);
    }

    @Data
    public static class KefuText implements Serializable{
        private static final long serialVersionUID = 6730861925023299031L;
        /**
         * 文本消息内容
         */
        private String content;

    }

}
