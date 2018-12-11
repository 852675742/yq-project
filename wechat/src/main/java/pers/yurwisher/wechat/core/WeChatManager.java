package pers.yurwisher.wechat.core;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.wechat.common.utils.Utils;
import pers.yurwisher.wechat.common.utils.crypto.SHA1;
import pers.yurwisher.wechat.exception.AesException;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.WxMessageRouter;
import pers.yurwisher.wechat.mp.in.WxMpXmlMessage;
import pers.yurwisher.wechat.mp.message.WxMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author yq
 * @date 2018/07/09 11:56
 * @description 微信消息处理
 * @since V1.0.0
 */
public class WeChatManager {

    private static final Logger logger = LoggerFactory.getLogger(WeChatManager.class);

    private WxMessageRouter wxMessageRouter;
    private MpService mpService;

    public WeChatManager(WxMessageRouter wxMessageRouter, MpService mpService) {
        this.wxMessageRouter = wxMessageRouter;
        this.mpService = mpService;
    }

    /**
     * 用户触发事件回调 (安全模式)
     * 安全模式: https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419318479&token=&lang=zh_CN
     * 明文模式: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319
     */
    public String post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        //微信加密签名
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");
        //加密类型
        String encryptType = request.getParameter("encrypt_type");

        logger.info("signature:{},timestamp:{},nonce:{},echostr:{},encryptType:{}", signature, timestamp, nonce, echostr, encryptType);

        //消息主体
        String postData = IOUtils.toString(request.getInputStream(), Charset.forName("UTF-8"));
        logger.info("postData:{}", postData);
        //明文
        if (Utils.isEmpty(encryptType)) {
            //映射成对象
            WxMpXmlMessage xmlMessage = WxMpXmlMessage.fromXml(postData);
            logger.info("消息类型:{},消息ID:{},用户openId:{}", xmlMessage.getMsgType(), xmlMessage.getMsgId(), xmlMessage.getFromUser());
            //消息经过路由后,返回处理消息
            WxMessage wxMessage = wxMessageRouter.route(xmlMessage);
            if (wxMessage != null) {
                return wxMessage.toXml();
            }
        } else if ("AES".equalsIgnoreCase(encryptType)) {
            //加密类型,为aes
            //消息签名，用于验证消息体的正确性
            String msgSignature = request.getParameter("msg_signature");
            //解密消息报文
            try {
                //消息解密后结果
                postData = mpService.getMsgCrypt().decryptMsg(msgSignature, timestamp, nonce, postData);
                //映射成对象
                WxMpXmlMessage xmlMessage = WxMpXmlMessage.fromXml(postData);
                logger.info("消息类型:{},消息ID:{},用户openId:{}", xmlMessage.getMsgType(), xmlMessage.getMsgId(), xmlMessage.getFromUser());
                //消息经过路由后,返回处理消息
                WxMessage wxMessage = wxMessageRouter.route(xmlMessage);
                if (wxMessage != null) {
                    //加密
                    String resultXml = mpService.getMsgCrypt().generateEncryptedXml(wxMessage.toXml());
                    logger.info("加密后的消息报文:{}", resultXml);
                    return resultXml;
                }
            } catch (AesException e) {
                logger.error("加解密工具类异常:{}", e.getMessage());
            }
        }
        return "";
    }

    /**
     * 微信服务器配置认证
     */
    public String get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //微信加密签名
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");

        logger.info("微信服务器配置认证:signature:{},timestamp:{},nonce:{},echostr:{}", signature, timestamp, nonce, echostr);

        //字段提取成功
        if (Utils.isNotEmpty(signature) && Utils.isNotEmpty(nonce)) {
            //token ,timestamp,nonce 字典序排序拼接成字符串,并通过sha1加密得到字符串
            String hashcode = SHA1.gen(mpService.getWxConfigRepository().getToken(), timestamp, nonce);
            //确定该数据是不是来源于微信后台
            boolean isFromWx = hashcode.equals(signature);
            logger.info("数据是否来源于微信后台:{}", isFromWx);
            if (isFromWx) {
                //来源于微信 返回echostr给微信,供微信后台认证token
                return echostr;
            }
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        return "";
    }


}
