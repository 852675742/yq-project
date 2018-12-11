package pers.yurwisher.wechat.mp.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.common.utils.xml.XStreamCDataConverter;

/**
 * @author yq
 * @date 2018/07/17 11:24
 * @description 接受的文本消息
 * @since V1.0.0
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxTextMessage extends WxMessage {

    private static final long serialVersionUID = -8463859342399801413L;

    public WxTextMessage() {
        super.msgType = WeChatConstant.MsgType.TEXT ;
    }

    @XStreamAlias("Content")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String content;
}
