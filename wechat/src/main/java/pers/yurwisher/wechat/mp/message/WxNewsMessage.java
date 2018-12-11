package pers.yurwisher.wechat.mp.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.constants.WeChatConstant;
import pers.yurwisher.wechat.common.utils.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信图文消息
 * @author yq
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxNewsMessage extends WxMessage {

  private static final long serialVersionUID = -3108272626372608321L;

  public WxNewsMessage() {
    super.msgType = WeChatConstant.MsgType.NEWS ;
  }

  /**
   * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
   */
  @XStreamAlias("Articles")
  protected final List<Item> articles = new ArrayList<>();

  /**
   * 图文消息个数，限制为8条以内
   */
  @XStreamAlias("ArticleCount")
  protected int articleCount;

  public void addArticle(List<Item> items) {
    this.articles.addAll(items);
    this.articleCount = this.articles.size();
  }

  @XStreamAlias("item")
  @Data
  public static class Item implements Serializable {
    private static final long serialVersionUID = -4971456355028904754L;

    /**
     * 图文消息标题
     */
    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    /**
     * 	图文消息描述
     */
    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;

    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*20
     */
    @XStreamAlias("PicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picUrl;

    /**
     *点击图文消息跳转链接
     */
    @XStreamAlias("Url")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String url;

  }


}
