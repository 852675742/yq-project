package pers.yurwisher.wechat.mp.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.common.utils.xml.XStreamCDataConverter;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/07/17 11:24
 * @description 音乐消息
 * @since V1.0.0
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMusicMessage extends WxMessage {

    private static final long serialVersionUID = 429007343314873184L;

    public WxMusicMessage() {
        super.msgType = WeChatConstant.MsgType.MUSIC ;
    }

    /**
     * 结构包装了一层
     */
    @XStreamAlias("Music")
    protected final Music music = new Music();

    @XStreamAlias("Music")
    @Data
    public static class Music implements Serializable {

        private static final long serialVersionUID = 5754647170726014652L;

        /**
         * 音乐标题
         */
        @XStreamAlias("Title")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String title;

        /**
         * 音乐描述
         */
        @XStreamAlias("Description")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String description;

        /**
         * 音乐链接
         */
        @XStreamAlias("MusicURL")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String musicUrl;

        /**
         * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
         */
        @XStreamAlias("HQMusicUrl")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String hqMusicUrl;

        /**
         * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
         */
        @XStreamAlias("ThumbMediaId")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String thumbMediaId;
    }


}
