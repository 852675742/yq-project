package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.mp.kf.message.KefuTextMessage;

/**
 * @author yq
 * @date 2018/07/31 15:02
 * @description 客服文本消息builder
 * @since V1.0.0
 */
public class KefuTextBuilder extends BaseKefuBuilder<KefuTextBuilder, KefuTextMessage> {

    /**
     * 小程序的文字链
     */
    private static final String A = "<a href=\"%1$s\" data-miniprogram-appid=\"%2$s\" data-miniprogram-path=\"%3$s\">%4$s</a>";

    /**
     * 文本消息内容
     */
    private String content;

    private String href;
    /**
     * 填写小程序appid，则表示该链接跳小程序
     */
    private String dataMiniProgramAppId;
    /**
     * 填写小程序路径，路径与app.json中保持一致，可带参数
     */
    private String dataMiniProgramPath;


    public KefuTextBuilder content(String content){
        this.content = content ;
        return this;
    }
    public KefuTextBuilder href(String href){
        this.href = href;
        return this;
    }
    public KefuTextBuilder dataMiniProgramAppId(String dataMiniProgramAppId){
        this.dataMiniProgramAppId = dataMiniProgramAppId;
        return this;
    }
    public KefuTextBuilder dataMiniProgramPath(String dataMiniProgramPath){
        this.dataMiniProgramPath = dataMiniProgramPath ;
        return this;
    }

    @Override
    public KefuTextMessage build() {
        KefuTextMessage message = new KefuTextMessage();
        super.setToUser(message);
        String content = Utils.isNotEmpty(dataMiniProgramAppId) ?  String.format(A,href,dataMiniProgramAppId,dataMiniProgramPath,this.content) : this.content ;
        message.getText().setContent(content);
        return message;
    }

}
