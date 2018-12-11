package pers.yurwisher.wechat.mp.menu.out;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义菜单配置
 * @author yq
 */
@Data
public class WxMpSelfMenuInfo implements Serializable {

    private static final long serialVersionUID = -1070265441594142957L;

    /**
     * 菜单按钮
     */
    @JSONField(name = "button")
    private List<WxMpSelfMenuButton> buttonList;

    @Data
    public static class WxMpSelfMenuButton implements Serializable {

        private static final long serialVersionUID = -5414244108741055700L;
        /**
         * 菜单的类型，公众平台官网上能够设置的菜单类型有view（跳转网页）、text（返回文本，下同）、img、photo、video、voice。
         * 使用API设置的则有8种，详见《自定义菜单创建接口》
         */
        private String type;
        /**
         * 菜单名称
         */
        private String name;

        /**
         * 对于不同的菜单类型，value的值意义不同。官网上设置的自定义菜单：
         * Text:保存文字到value；
         * Img、voice：保存mediaID到value；
         * Video：保存视频下载链接到value；
         * News：保存图文消息到news_info，同时保存mediaID到value；
         * View：保存链接到url。
         * 使用API设置的自定义菜单：
         * click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、 pic_weixin、location_select：保存值到key；
         * view：保存链接到url
         */
        private String key;
        /**
         * @see #key
         */
        private String url;
        /**
         * @see #key
         */
        private String value;
        /**
         * 子菜单信息
         */
        @JSONField(name = "sub_button")
        private SubButtons subButton;
        /**
         * 图文消息的信息,通过官网设置的才有此值
         */
        @JSONField(name = "news_info")
        private NewsInfo newsInfo;

        /**
         * 子菜单集合
         */
        @Data
        public static class SubButtons implements Serializable {
            private static final long serialVersionUID = 1763350658575521079L;

            @JSONField(name = "list")
            private List<WxMpSelfMenuButton> list = new ArrayList<>();

        }

        /**
         * 图文消息
         */
        @Data
        public static class NewsInfo implements Serializable {
            private static final long serialVersionUID = 3449813746347818457L;

            @JSONField(name = "list")
            private List<NewsInButton> news = new ArrayList<>();

            @Data
            public static class NewsInButton implements Serializable {
                private static final long serialVersionUID = 8701455967664912972L;

                /**
                 * 图文消息的标题
                 */
                private String title;
                /**
                 * 摘要
                 */
                private String digest;
                /**
                 * 作者
                 */
                private String author;
                /**
                 * 是否显示封面，0为不显示，1为显示
                 */
                @JSONField(name = "show_cover")
                private Integer showCover;
                /**
                 * 封面图片的URL
                 */
                @JSONField(name = "cover_url")
                private String coverUrl;
                /**
                 * 正文的URL
                 */
                @JSONField(name = "content_url")
                private String contentUrl;
                /**
                 * 原文的URL，若置空则无查看原文入口
                 */
                @JSONField(name = "source_url")
                private String sourceUrl;
            }
        }
    }
}
