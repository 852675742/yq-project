package pers.yurwisher.wechat.mp.kf.message;


import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.mp.kf.KefuMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yq
 * @date 2018/07/31 15:15
 * @description 客服图文消息 点击跳转到外链,如果图文数超过8，则将会无响应
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuNewsMessage extends KefuMessage {


    private static final long serialVersionUID = -2040453913170434330L;
    private KefuNews news ;

    public KefuNewsMessage() {
        this.news = new KefuNews();
        super.setMsgType(WeChatConstant.KefuMsgType.NEWS);
    }

    @Data
    public static class KefuNews implements Serializable {

        private static final long serialVersionUID = -8131022919405789522L;

        private List<Article> articles = new ArrayList<>();

        @Data
        public static class Article implements Serializable{

            private static final long serialVersionUID = 2342097155683325217L;
            /**标题*/
            private String title;
            /**描述*/
            private String description;
            /**图文消息被点击后跳转的链接*/
            private String url;
            /**图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80*/
            private String picUrl;
        }
    }
}
