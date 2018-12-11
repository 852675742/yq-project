package pers.yurwisher.wechat.common.base;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/07/20 17:08
 * @description 公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token
 * @since V1.0.0
 */
public class WxAccessToken implements Serializable {

    private static final long serialVersionUID = -4834744885203635941L;

    /**
     * 获取到的凭证
     */
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒
     */
    private int expiresIn = -1;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
