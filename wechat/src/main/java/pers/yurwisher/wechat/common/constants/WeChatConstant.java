package pers.yurwisher.wechat.common.constants;

/**
 * @author yq
 * @date 2018/05/30 11:33
 * @description 微信接口常量
 * @since V1.0.0
 */
public interface WeChatConstant {

    /**
     * 微信公众号成功返回编码
     */
    String SUCCESS_CODE = "0";

    /**
     * 微信返回码 key
     */
    String ERROR_CODE_KEY = "errcode";
    /**
     * 微信返回提示 key
     */
    String ERROR_MSG_KEY = "errmsg";

    /**
     * 微信推送过来的消息的类型，和发送给微信xml格式消息的消息类型.
     */
    interface MsgType {
        String TEXT = "text";
        String IMAGE = "image";
        String VOICE = "voice";
        String SHORTVIDEO = "shortvideo";
        String VIDEO = "video";
        String NEWS = "news";
        String MUSIC = "music";
        String LOCATION = "location";
        String LINK = "link";
        String EVENT = "event";
        String DEVICE_TEXT = "device_text";
        String DEVICE_EVENT = "device_event";
        String DEVICE_STATUS = "device_status";
        String HARDWARE = "hardware";
        String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
    }

    /**
     * 微信端推送过来的事件类型.
     */
    interface EventType {
        /**订阅*/
        String SUBSCRIBE = "subscribe";
        /**取消订阅*/
        String UNSUBSCRIBE = "unsubscribe";
        String SCAN = "SCAN";
        String LOCATION = "LOCATION";
        String CLICK = "CLICK";
        String VIEW = "VIEW";
        String MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH";
        String SCANCODE_PUSH = "scancode_push";
        String SCANCODE_WAITMSG = "scancode_waitmsg";
        String PIC_SYSPHOTO = "pic_sysphoto";
        String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
        String PIC_WEIXIN = "pic_weixin";
        String LOCATION_SELECT = "location_select";
        String TEMPLATE_SEND_JOB_FINISH = "TEMPLATESENDJOBFINISH";
        String ENTER_AGENT = "enter_agent";
    }

    /**
     * 主动发送消息(即客服消息)的消息类型.
     */
    interface KefuMsgType {
        /**
         * 文本消息.
         */
        String TEXT = "text";
        /**
         * 图片消息.
         */
        String IMAGE = "image";
        /**
         * 语音消息.
         */
       String VOICE = "voice";
        /**
         * 视频消息.
         */
       String VIDEO = "video";
        /**
         * 音乐消息.
         */
       String MUSIC = "music";
        /**
         * 图文消息（点击跳转到外链）.
         */
       String NEWS = "news";
        /**
         * 图文消息（点击跳转到图文消息页面）.
         */
       String MPNEWS = "mpnews";
        /**
         * 发送文件（CP专用）.
         */
       String FILE = "file";
        /**
         * 文本卡片消息（CP专用）.
         */
       String TEXTCARD = "textcard";
        /**
         * 卡券消息.
         */
       String WXCARD = "wxcard";
        /**
         * 转发到客服的消息.
         */
       String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";

        /**
         * 小程序卡片(要求小程序与公众号已关联)
         */
       String MINIPROGRAMPAGE="miniprogrampage";
    }
}
