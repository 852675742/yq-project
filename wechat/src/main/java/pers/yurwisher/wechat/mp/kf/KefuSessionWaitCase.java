package pers.yurwisher.wechat.mp.kf;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yq
 * @date 2018/07/31 11:22
 * @description 未接入会话列表
 * @since V1.0.0
 */
@Data
public class KefuSessionWaitCase implements Serializable{

    private static final long serialVersionUID = -3738745898675200382L;
    /**
     * 	未接入会话数量
     */
    private Long count;

    /**
     * 未接入会话列表，最多返回100条数据，按照来访顺序
     */
    @JSONField(name ="waitcaselist")
    private List<KefuSession> waitCaseList;

}
