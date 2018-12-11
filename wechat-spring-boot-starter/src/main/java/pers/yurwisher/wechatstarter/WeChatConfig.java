package pers.yurwisher.wechatstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yq
 * @date 2018/12/06 14:54
 * @description 微信公众号配置
 * @since V1.0.0
 */
@ConfigurationProperties(prefix = "yurwisher.wechat")
public class WeChatConfig {

    /**公众号appId*/
    private String appId ;
    /**公众号密钥*/
    private String secret;
    /**服务器配置令牌 token*/
    private String serverToken;
    /**服务器配置 消息加解密密钥(EncodingAESKey)*/
    private String aesKey;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServerToken() {
        return serverToken;
    }

    public void setServerToken(String serverToken) {
        this.serverToken = serverToken;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }
}
