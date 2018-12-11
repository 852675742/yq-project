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
 * @description 图片消息
 * @since V1.0.0
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxImageMessage extends WxMessage {

    private static final long serialVersionUID = 5722482407429199685L;

    public WxImageMessage() {
        super.msgType = WeChatConstant.MsgType.IMAGE ;
    }

    /**
     * 结构包装了一层
     */
    @XStreamAlias("Image")
    protected final Image video = new Image();

    @XStreamAlias("Image")
    @Data
    public static class Image implements Serializable {

        private static final long serialVersionUID = 262657926161499628L;
        /**
         * 通过素材管理中的接口上传多媒体文件，得到的id
         */
        @XStreamAlias("MediaId")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String mediaId;
    }
}
