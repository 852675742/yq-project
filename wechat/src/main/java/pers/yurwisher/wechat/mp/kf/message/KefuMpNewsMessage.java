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
 * @description 发送图文消息点击跳转到图文消息页面
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuMpNewsMessage extends KefuMessage {

    private static final long serialVersionUID = 4268552511386337303L;
    @JSONField(name = "mpnews")
    private KefuMpNews mpNews ;

    public KefuMpNewsMessage() {
        this.mpNews = new KefuMpNews();
        super.setMsgType(WeChatConstant.KefuMsgType.MPNEWS);
    }

    @Data
    public static class KefuMpNews implements Serializable {
        private static final long serialVersionUID = -3425986492750700414L;

        /**
         * 通过素材管理中的接口上传多媒体文件，得到的id
         */
        @JSONField(name = "media_id")
        private String mediaId;

    }

}
