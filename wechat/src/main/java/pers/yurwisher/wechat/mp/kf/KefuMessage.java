package pers.yurwisher.wechat.mp.kf;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.base.JsonBean;
import pers.yurwisher.wechat.mp.kf.builder.KefuImageBuilder;
import pers.yurwisher.wechat.mp.kf.builder.KefuMiniProgramPageBuilder;
import pers.yurwisher.wechat.mp.kf.builder.KefuMpNewsBuilder;
import pers.yurwisher.wechat.mp.kf.builder.KefuMusicBuilder;
import pers.yurwisher.wechat.mp.kf.builder.KefuNewsBuilder;
import pers.yurwisher.wechat.mp.kf.builder.KefuTextBuilder;
import pers.yurwisher.wechat.mp.kf.builder.KefuVideoBuilder;
import pers.yurwisher.wechat.mp.kf.builder.KefuVoiceBuilder;
import pers.yurwisher.wechat.mp.kf.builder.KefuWxCardBuilder;

/**
 * @author yq
 * @date 2018/07/31 12:14
 * @description 客服消息
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuMessage extends JsonBean {

    private static final long serialVersionUID = 6609937182648437152L;

    /**
     * 普通用户openid
     */
    @JSONField(name = "touser")
    private String toUser;

    /**
     * 普通用户openid
     */
    @JSONField(name = "msgtype")
    private String msgType;

    /**
     * 获得文本消息builder
     */
    public static KefuTextBuilder text() {
        return new KefuTextBuilder();
    }

    /**
     * 获得图片消息builder
     */
    public static KefuImageBuilder image() {
        return new KefuImageBuilder();
    }

    /**
     * 获得语音消息builder
     */
    public static KefuVoiceBuilder voice() {
        return new KefuVoiceBuilder();
    }

    /**
     * 获得视频消息builder
     */
    public static KefuVideoBuilder video() {
        return new KefuVideoBuilder();
    }

    /**
     * 获得音乐消息builder
     */
    public static KefuMusicBuilder music() {
        return new KefuMusicBuilder();
    }

    /**
     * 获得图文消息builder （点击跳转到外链）
     */
    public static KefuNewsBuilder news() {
        return new KefuNewsBuilder();
    }

    /**
     * 获得图文消息builder （点击跳转到图文消息页面）
     */
    public static KefuMpNewsBuilder mpNews() {
        return new KefuMpNewsBuilder();
    }

    /**
     * 发送卡券
     */
    public static KefuWxCardBuilder wxCard() {
        return new KefuWxCardBuilder();
    }

    /**
     * 发送小程序卡片
     */
    public static KefuMiniProgramPageBuilder miniProgramPage() {
        return new KefuMiniProgramPageBuilder();
    }


}
