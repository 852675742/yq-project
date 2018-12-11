package pers.yurwisher.wechat.mp.kf.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.mp.kf.KefuMessage;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/07/31 16:28
 * @description 发送小程序卡片（要求小程序与公众号已关联）
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuMiniProgramPageMessage extends KefuMessage {
    private static final long serialVersionUID = 8510811864692703774L;

    @JSONField(name = "miniprogrampage")
    private KefuMiniProgramPage miniProgramPage;

    public KefuMiniProgramPageMessage() {
        this.miniProgramPage = new KefuMiniProgramPage();
        super.setMsgType(WeChatConstant.KefuMsgType.MINIPROGRAMPAGE);
    }

    @Data
    public static class KefuMiniProgramPage implements Serializable {

        private static final long serialVersionUID = -4364586881318836256L;

        /**标题*/
        private String title;
        /**
         * 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
         */
        @JSONField(name = "thumb_media_id")
        private String thumbMediaId;

        /**
         * 小程序的appid，要求小程序的appid需要与公众号有关联关系
         */
        @JSONField(name = "appid")
        private String appId;
        /**
         * 小程序的页面路径，跟app.json对齐，支持参数，比如pages/index/index?foo=bar
         */
        @JSONField(name = "pagepath")
        private String pagePath ;
    }

}
