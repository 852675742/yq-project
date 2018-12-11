package pers.yurwisher.wechat.mp.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.base.WxAccessToken;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.WxConfigRepository;


/**
 * @author yq
 * @date 2018/07/20 15:03
 * @description 默认微信配置存储 内存
 * @since V1.0.0
 */
public class DefaultWxConfigRepository implements WxConfigRepository {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWxConfigRepository.class);

    private MpService mpService;

    public void setMpService(MpService mpService) {
        this.mpService = mpService;
    }

    /**
     * 公众号对应appId 开发者ID
     */
    protected volatile String appId;
    /**
     * 开发者密码(AppSecret)
     */
    protected volatile String secret;
    /**
     * 服务器配置令牌 token
     */
    protected volatile String token;
    /**
     * 服务器配置 消息加解密密钥(EncodingAESKey)
     */
    protected volatile String aesKey;
    /**
     * 微信接口请求token
     */
    protected volatile String accessToken;
    /**
     * 微信接口请求token 有效时间
     */
    protected volatile long expiresTime = -1L;

    @Override
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    @Override
    public String getAccessToken() {
        //token已作废
        if(expiresTime == -1L || expiresTime - System.currentTimeMillis() <= 0){
            if(isAutoRefresh()){
                WxAccessToken newToken = mpService.getTokenService().getAccessToken(appId,secret);
                updateAccessToken(newToken);
            }
        }
        return accessToken ;
    }

    @Override
    public long getExpiresTime() {
        return expiresTime;
    }

    @Override
    public boolean isAutoRefresh() {
        return true;
    }

    @Override
    public void updateAccessToken(WxAccessToken newToken) {
        Long now = System.currentTimeMillis();
        logger.info("刷新token,旧token有效期还剩余:{}",now - expiresTime );
        this.accessToken = newToken.getAccessToken() ;
        this.expiresTime = now + (newToken.getExpiresIn() - 200) * 1000L;
    }

    @Override
    public void expireAccessToken() {
        this.expiresTime = -1L;
    }

}
