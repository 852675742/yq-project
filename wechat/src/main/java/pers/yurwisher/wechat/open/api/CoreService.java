package pers.yurwisher.wechat.open.api;

import com.alibaba.fastjson.JSONObject;
import pers.yurwisher.wechat.exception.WeChatException;
import pers.yurwisher.wechat.open.bean.JsCode2Session;

/**
 * @author yq
 * @date 2018/12/17 13:46
 * @description 核心
 * @since V1.0.0
 */
public interface CoreService {

   String JS_CODE_TO_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%1$s&secret=%2$s&js_code=%3$s&grant_type=authorization_code";

   /**
    * 获取登录凭证校验结果
    * @param code 登录时获取的 code
    * @return  登录凭证校验结果
    */
   JsCode2Session getJsCode2Session(String code);

   /**
    * 校验接口返回并转为json
    * @param responseStr 微信接口返回
    * @return JSONObject
    * @throws WeChatException
    */
   JSONObject judgeValidParseJSON(String responseStr) throws WeChatException;
}
