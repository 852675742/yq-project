package pers.yurwisher.wechat.mp.kf.builder;


import pers.yurwisher.wechat.mp.kf.message.KefuMiniProgramPageMessage;

/**
 * @author yq
 * @date 2018/07/31 16:15
 * @description 发送小程序卡片（要求小程序与公众号已关联）
 * @since V1.0.0
 */
public class KefuMiniProgramPageBuilder extends BaseKefuBuilder<KefuMiniProgramPageBuilder, KefuMiniProgramPageMessage> {

    /**标题*/
    private String title;
    /**
     * 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
     */
    private String thumbMediaId;
    /**
     * 小程序的appid，要求小程序的appid需要与公众号有关联关系
     */
    private String appId;
    /**
     * 小程序的页面路径，跟app.json对齐，支持参数，比如pages/index/index?foo=bar
     */
    private String pagePath ;

    public KefuMiniProgramPageBuilder title(String title) {
        this.title = title;
        return this ;
    }

    public KefuMiniProgramPageBuilder thumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
        return this ;
    }

    public KefuMiniProgramPageBuilder appId(String appId) {
        this.appId = appId;
        return this ;
    }

    public KefuMiniProgramPageBuilder pagePath(String pagePath) {
        this.pagePath = pagePath;
        return this ;
    }

    @Override
    public KefuMiniProgramPageMessage build() {
        KefuMiniProgramPageMessage message = new KefuMiniProgramPageMessage();
        super.setToUser(message);
        message.getMiniProgramPage().setAppId(appId);
        message.getMiniProgramPage().setThumbMediaId(thumbMediaId);
        message.getMiniProgramPage().setPagePath(pagePath);
        message.getMiniProgramPage().setTitle(title);
        return message;
    }
}
