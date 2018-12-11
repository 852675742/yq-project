package pers.yurwisher.wechat.mp.kf;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 客服聊天记录
 * @author  yq
 */
@Data
public class KefuMsgRecord implements Serializable {

  private static final long serialVersionUID = 788124244887666912L;

  /**
   * 聊天记录集合
   */
  @JSONField(name = "recordlist")
  private List<KefuMsg> recordList;

  /**
   * 复核查询接口的消息数量
   */
  private Long number;

  /**
   * 起始的消息ID
   */
  @JSONField(name = "msgid")
  private Long msgId;

  @Data
  public static class KefuMsg implements Serializable {

    private static final long serialVersionUID = -4881723276476555307L;
    /**
     * 完整客服帐号，格式为：帐号前缀@公众号微信号
     */
    private String worker;

    /**
     * 用户标识
     */
    @JSONField(name = "openid")
    private String openId;

    /**
     * 操作码，2002（客服发送信息），2003（客服接收消息）
     */
    @JSONField(name ="opercode")
    private Integer operateCode;

    /**
     * 聊天记录
     */
    private String text;

    /**
     * 操作时间，unix时间戳
     */
    private Long time;
  }

}
