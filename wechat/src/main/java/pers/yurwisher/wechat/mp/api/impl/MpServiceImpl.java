package pers.yurwisher.wechat.mp.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.common.enums.MpErrorEnum;
import pers.yurwisher.wechat.common.enums.WxType;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.common.utils.crypto.MsgCrypt;
import pers.yurwisher.wechat.common.utils.http.HttpRequest;
import pers.yurwisher.wechat.common.utils.http.apache.HttpRequestApacheImpl;
import pers.yurwisher.wechat.exception.WeChatException;
import pers.yurwisher.wechat.mp.api.KefuService;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.TemplateService;
import pers.yurwisher.wechat.mp.api.TokenService;
import pers.yurwisher.wechat.mp.api.WxConfigRepository;
import pers.yurwisher.wechat.mp.api.WxMenuService;

import java.util.List;


/**
 * @author yq
 * @date 2018/07/26 14:52
 * @description 公众号service
 * @since V1.0.0
 */
public class MpServiceImpl implements MpService {

    private static final Logger logger = LoggerFactory.getLogger(MpServiceImpl.class);

    private TemplateService templateService = new TemplateServiceImpl(this);
    private TokenService tokenService = new TokenServiceImpl(this);
    private HttpRequest httpRequest;
    private WxConfigRepository wxConfigRepository;
    private MsgCrypt msgCrypt ;
    private WxMenuService wxMenuService = new WxMenuServiceImpl(this);
    private KefuService kefuService = new KefuServiceImpl(this);

    public MpServiceImpl(WxConfigRepository wxConfigRepository) {
        this.wxConfigRepository = wxConfigRepository;
        this.httpRequest = HttpRequestApacheImpl.INSTANCE;
    }

    public MpServiceImpl(HttpRequest httpRequest, WxConfigRepository wxConfigRepository) {
        this.httpRequest = httpRequest;
        this.wxConfigRepository = wxConfigRepository;
    }

    @Override
    public TemplateService getTemplateService() {
        return templateService;
    }

    @Override
    public TokenService getTokenService() {
        return tokenService;
    }

    @Override
    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    @Override
    public WxConfigRepository getWxConfigRepository() {
        return wxConfigRepository;
    }

    @Override
    public JSONObject judgeValidParseJSON(String responseStr, WxType type) {
        if (Utils.isNotEmpty(responseStr)) {
            JSONObject json = JSON.parseObject(responseStr);
            String errorCode = json.getString(WeChatConstant.ERROR_CODE_KEY);
            //接口返回包含错误码,且不为成功码0
            if (Utils.isNotEmpty(errorCode) && !WeChatConstant.SUCCESS_CODE.equals(errorCode)) {
                /*
                   * 发生以下情况时尝试刷新access_token
                   * 40001 获取access_token时AppSecret错误，或者access_token无效
                   * 42001 access_token超时
                   * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
                    */
                if (Integer.toString(MpErrorEnum.CODE_42001.getCode()).equals(errorCode)
                        || Integer.toString(MpErrorEnum.CODE_40001.getCode()).equals(errorCode)
                        || Integer.toString(MpErrorEnum.CODE_40014.getCode()).equals(errorCode)) {
                    //作废token 下次请求自动刷新
                    logger.info("token已无效,自动刷新token");
                    wxConfigRepository.expireAccessToken();
                }
                logger.error("错误码:{},详情:{}", errorCode, json.getString(WeChatConstant.ERROR_MSG_KEY));
                /**优先返回中文信息,微信接口返回为英文提示*/
                String description = MpErrorEnum.getDescriptionByCode(errorCode);
                description = Utils.isNotEmpty(description) ? description : json.getString(WeChatConstant.ERROR_MSG_KEY);
                throw new WeChatException(description);
            }
            return json;
        } else {
            logger.error("请求异常,无返回结果");
            throw new WeChatException("接口请求失败!");
        }
    }

    @Override
    public MsgCrypt getMsgCrypt() {
        if(msgCrypt == null){
            msgCrypt = new MsgCrypt(wxConfigRepository.getToken(),wxConfigRepository.getAesKey(),wxConfigRepository.getAppId());
        }
        return msgCrypt;
    }

    @Override
    public WxMenuService getWxMenuService() {
        return wxMenuService;
    }

    @Override
    public List<String> getCallbackIP() {
        String responseStr = httpRequest.getWithToken(GET_CALL_BACK_IP_URL ,wxConfigRepository.getAccessToken());
        JSONObject json =  judgeValidParseJSON(responseStr, WxType.MP);
        JSONArray array = json.getJSONArray("ip_list");
        if(Utils.isNotEmpty(array)){
            return array.toJavaList(String.class);
        }
        return null;
    }

    @Override
    public KefuService getKefuService() {
        return kefuService;
    }

}
