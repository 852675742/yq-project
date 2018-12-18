package pers.yurwisher.wechat.mp.api;


import pers.yurwisher.wechat.common.base.WxAccessToken;
import pers.yurwisher.wechat.core.BaseConfigRepository;

/**
 * @author yq
 * @date 2018/07/20 16:51
 * @description 微信配置存储
 * @since V1.0.0
 */
public interface MpConfigRepository extends BaseConfigRepository {

    /**服务器配置令牌 token*/
    String getToken();

    /**服务器配置 消息加解密密钥(EncodingAESKey)*/
    String getAesKey();

    /**微信接口请求token*/
    String getAccessToken();

    /**微信接口请求token 有效时间*/
    long getExpiresTime();

    /**是否自动刷新token*/
    boolean isAutoRefresh();

    /**
     * 更新token
     */
    void updateAccessToken(WxAccessToken newToken);

    /**
     * 作废token
     */
    void expireAccessToken();

}
