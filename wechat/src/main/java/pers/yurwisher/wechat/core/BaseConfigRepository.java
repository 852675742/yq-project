package pers.yurwisher.wechat.core;

/**
 * @author yq
 * @date 2018/12/18 14:33
 * @description 基础微信配置
 * @since V1.0.0
 */
public interface BaseConfigRepository {

    /**
     * appId
     * @return appId
     */
    String getAppId();


    /**
     * secret
     * @return secret
     */
    String getSecret();
}
