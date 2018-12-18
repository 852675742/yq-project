package pers.yurwisher.wechat.open.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.common.enums.MpErrorEnum;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.common.utils.http.HttpRequest;
import pers.yurwisher.wechat.common.utils.http.apache.HttpRequestApacheImpl;
import pers.yurwisher.wechat.exception.WeChatException;
import pers.yurwisher.wechat.open.api.CoreService;
import pers.yurwisher.wechat.open.api.MiniAppConfigRepository;
import pers.yurwisher.wechat.open.bean.JsCode2Session;

/**
 * @author yq
 * @date 2018/12/17 13:46
 * @description 核心
 * @since V1.0.0
 */
public class CoreServiceImpl implements CoreService {

    private static final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

    private HttpRequest httpRequest ;
    private MiniAppConfigRepository miniAppConfigRepository;

    public CoreServiceImpl(HttpRequest httpRequest,MiniAppConfigRepository miniAppConfigRepository) {
        this.httpRequest = httpRequest;
        this.miniAppConfigRepository = miniAppConfigRepository;
    }
    public CoreServiceImpl(MiniAppConfigRepository miniAppConfigRepository) {
        this(HttpRequestApacheImpl.INSTANCE, miniAppConfigRepository);
    }

    @Override
    public JsCode2Session getJsCode2Session(String code) {
        String url = String.format(JS_CODE_TO_SESSION_URL,miniAppConfigRepository.getAppId(),miniAppConfigRepository.getSecret(),code);
        JSONObject jsonObject = judgeValidParseJSON(httpRequest.get(url));
        return jsonObject.toJavaObject(JsCode2Session.class);
    }

    @Override
    public JSONObject judgeValidParseJSON(String responseStr) throws WeChatException {
        if (Utils.isNotEmpty(responseStr)) {
            JSONObject json = JSON.parseObject(responseStr);
            String errorCode = json.getString(WeChatConstant.ERROR_CODE_KEY);
            //接口返回包含错误码,且不为成功码0
            if (Utils.isNotEmpty(errorCode) && !WeChatConstant.SUCCESS_CODE.equals(errorCode)) {
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
}
