package pers.yurwisher.wechat.mp.kf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.base.JsonBean;

/**
 * @author yq
 * @date 2018/07/30 16:56
 * @description 客服帐号
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuAccount extends JsonBean {

    private static final long serialVersionUID = -2910581216232971975L;
    /**
     * 完整客服账号，格式为：账号前缀@公众号微信号
     * 帐号前缀最多10个字符，必须是英文、数字字符或者下划线，
     * 后缀为公众号微信号，长度不超过30个字符
     */
    @JSONField(name = "kf_account")
    private String kfAccount;

    /**
     * 客服昵称
     */
    @JSONField(name = "kf_nick")
    private String kfNick;

    /**
     * 	客服工号
     */
    @JSONField(name = "kf_id")
    private String kfId;

    /**
     * 	客服头像
     */
    @JSONField(name = "kf_headimgurl")
    private String kfHeadImgUrl;

    /**
     * 	如果客服帐号已绑定了客服人员微信号， 则此处显示微信号
     */
    @JSONField(name = "kf_wx")
    private String kfWx;

    /**
     * 	如果客服帐号尚未绑定微信号，但是已经发起了一个绑定邀请，
     * 	则此处显示绑定邀请的微信号
     */
    @JSONField(name = "invite_wx")
    private String inviteWx;

    /**
     * 	如果客服帐号尚未绑定微信号，但是已经发起过一个绑定邀请，
     * 	邀请的过期时间，为unix 时间戳
     */
    @JSONField(name = "invite_expire_time")
    private Long inviteExpireTime;

    /**
     * 	邀请的状态，
     * 	有等待确认“waiting”，
     * 	被拒绝“rejected”，
     * 	过期“expired”
     */
    @JSONField(name = "invite_status")
    private String inviteStatus;

    /**
     * 客服在线状态，目前为：1、web 在线
     */
    private String status;

    /**
     * 客服当前正在接待的会话数
     */
    @JSONField(name = "accepted_case")
    private Integer acceptedCase;

    /**
     * 客服昵称,最长16个字
     */
    @JSONField(name = "nickname")
    private String nickName;

    @Override
    public String toJSON() {
        return JSON.toJSONString(this);
    }

}
