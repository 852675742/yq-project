package pers.yurwisher.wechat.mp.api;


import pers.yurwisher.wechat.common.base.WxAccessToken;

/**
 * @author yq
 * @date 2018/07/09 18:20
 * @description token service
 * @since V1.0.0
 */
public interface TokenService {

    /**
     * 获取token地址
     */
    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%1$s&secret=%2$s";

    /**
     * 获取token
     * @param appId appId
     * @param secret secret
     * @return token
     */
    WxAccessToken getAccessToken(String appId, String secret);

}
