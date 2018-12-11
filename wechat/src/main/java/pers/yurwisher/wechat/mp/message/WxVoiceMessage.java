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
 * @description 语音消息
 * @since V1.0.0
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxVoiceMessage extends WxMessage {

    private static final long serialVersionUID = -2950667910869926783L;

    public WxVoiceMessage() {
        super.msgType = WeChatConstant.MsgType.VOICE ;
    }

    /**
     * 结构包装了一层
     */
    @XStreamAlias("Voice")
    protected final Voice voice = new Voice();

    @XStreamAlias("Voice")
    @Data
    public static class Voice implements Serializable {

        private static final long serialVersionUID = -4294386897491239759L;
        /**
         * 通过素材管理中的接口上传多媒体文件，得到的id
         */
        @XStreamAlias("MediaId")
        @XStreamConverter(value = XStreamCDataConverter.class)
        private String mediaId;
    }

}
