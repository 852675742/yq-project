package pers.yurwisher.wechat.mp.kf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.base.JsonBean;

/**
 * @author yq
 * @date 2018/07/31 10:58
 * @description 客服会话
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuSession extends JsonBean {

    private static final long serialVersionUID = -1070646638573939751L;
    /**
     * 完整客服帐号，格式为：帐号前缀@公众号微信号
     * 正在接待的客服，为空表示没有人在接待
     */
    @JSONField(name = "kf_account")
    private String kfAccount;

    /**
     * createtime 会话接入的时间，UNIX时间戳
     * 该返回值 存在于 获取客服会话列表接口
     */
    @JSONField(name = "createtime")
    private long createTime;

    /**
     * latest_time 粉丝的最后一条消息的时间，UNIX时间戳
     * 该返回值 存在于 获取未接入会话列表接口
     */
    @JSONField(name = "latest_time")
    private long latestTime;

    /**
     * 粉丝的openid
     */
    @JSONField(name = "openid")
    private String openId;

    @Override
    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
