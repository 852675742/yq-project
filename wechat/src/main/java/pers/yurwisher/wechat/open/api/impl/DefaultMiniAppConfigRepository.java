package pers.yurwisher.wechat.open.api.impl;

import pers.yurwisher.wechat.open.api.MiniAppConfigRepository;

/**
 * @author yq
 * @date 2018/12/18 15:03
 * @description 默认小程序配置
 * @since V1.0.0
 */
public class DefaultMiniAppConfigRepository implements MiniAppConfigRepository {

    /**
     * 公众号对应appId 开发者ID
     */
    private volatile String appId;
    /**
     * 开发者密码(AppSecret)
     */
    private volatile String secret;

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
}
