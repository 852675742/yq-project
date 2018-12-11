package pers.yurwisher.wechat.mp.api.impl;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.base.WxAccessToken;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.TokenService;


/**
 * @author yq
 * @date 2018/07/11 13:51
 * @description token
 * @since V1.0.0
 */
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);


    private MpService mpService;

    public TokenServiceImpl(MpService mpService) {
        this.mpService = mpService;
    }

    /**
     * 调用微信接口获取最新token
     */
    @Override
    public WxAccessToken getAccessToken(String appId, String secret) {
        String responseStr = mpService.getHttpRequest().get(String.format(ACCESS_TOKEN_URL,appId,secret));
        JSONObject json = mpService.judgeValidParseJSON(responseStr, WxType.MP);
        WxAccessToken accessToken = json.toJavaObject(WxAccessToken.class);
        logger.info("公众号最新token:{}", accessToken.getAccessToken());
        return accessToken;
    }


}
