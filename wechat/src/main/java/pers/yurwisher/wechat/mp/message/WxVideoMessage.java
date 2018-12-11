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
 * @description 视频消息
 * @since V1.0.0
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxVideoMessage extends WxMessage {

    private static final long serialVersionUID = 8910298283218006358L;

    public WxVideoMessage() {
        super.msgType = WeChatConstant.MsgType.VIDEO ;
    }

    /**
     * 结构包装了一层
     */
    @XStreamAlias("Video")
    protected final Video video = new Video();

    @XStreamAlias("Video")
    @Data
    public static class Video implements Serializable {

        private static final long serialVersionUID = -5849474104474525705L;
        /**
         * 通过素材管理中的接口上传多媒体文件，得到的id
         */
        @XStreamAlias("MediaId")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String mediaId;

        /**
         * 视频消息的标题
         */
        @XStreamAlias("Title")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String title;

        /**
         * 视频消息的描述
         */
        @XStreamAlias("Description")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String description;

    }

}
