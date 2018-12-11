package pers.yurwisher.wechat.mp.kf;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.base.JsonBean;

/**
 * @author yq
 * @date 2018/07/31 11:45
 * @description 获取客服聊天记录请求对象
 * @since V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KefuMsgRequest extends JsonBean {

    private static final long serialVersionUID = -1827938812499858989L;
    /**
     * 起始时间，unix时间戳
     */
    @JSONField(name = "starttime")
    private Long  startTime;

    /**
     * 结束时间，unix时间戳，每次查询时段不能超过24小时
     */
    @JSONField(name = "endtime")
    private Long endTime ;

    /**
     * 消息id顺序从小到大，从1开始
     */
    @JSONField(name = "msgid")
    private Long  msgId	;

    /**
     * 每次获取条数，最多10000条
     */
    private Long number	;

}
