package pers.yurwisher.wechat.mp.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import pers.yurwisher.wechat.common.utils.xml.XStreamCDataConverter;
import pers.yurwisher.wechat.common.utils.xml.XStreamTransformer;
import pers.yurwisher.wechat.mp.builder.ImageBuilder;
import pers.yurwisher.wechat.mp.builder.MusicBuilder;
import pers.yurwisher.wechat.mp.builder.NewsBuilder;
import pers.yurwisher.wechat.mp.builder.TextBuilder;
import pers.yurwisher.wechat.mp.builder.VideoBuilder;
import pers.yurwisher.wechat.mp.builder.VoiceBuilder;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/07/17 11:20
 * @description 微信消息推送的基类
 * @since V1.0.0
 */
@XStreamAlias("xml")
@Data
public class WxMessage implements Serializable {

    private static final long serialVersionUID = 3192018746836945513L;
    /**
     * 	开发者微信号
     */
    @XStreamAlias("ToUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String toUserName;

    /**
     * 	发送方帐号（一个OpenID）
     */
    @XStreamAlias("FromUserName")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @XStreamAlias("CreateTime")
    protected Long createTime;

    /**
     * 消息类型
     */
    @XStreamAlias("MsgType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    protected String msgType;

    /**
     * 获得文本消息builder
     */
    public static TextBuilder text() {
        return new TextBuilder();
    }

    /**
     * 获得图片消息builder
     */
    public static ImageBuilder image() {
        return new ImageBuilder();
    }

    /**
     * 获得语音消息builder
     */
    public static VoiceBuilder voice() {
        return new VoiceBuilder();
    }

    /**
     * 获得视频消息builder
     */
    public static VideoBuilder video() {
        return new VideoBuilder();
    }

    /**
     * 获得音乐消息builder
     */
    public static MusicBuilder music() {
        return new MusicBuilder();
    }

    /**
     * 获得图文消息builder
     */
    public static NewsBuilder news() {
        return new NewsBuilder();
    }

    /**
     * 对象转xml字符串
     * @return xml
     */
    @SuppressWarnings("unchecked")
    public String toXml() {
        return XStreamTransformer.toXml((Class<WxMessage>) this.getClass(), this);
    }

}
