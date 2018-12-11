package pers.yurwisher.wechat.mp.builder;



import pers.yurwisher.wechat.mp.message.WxNewsMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 图文消息builder
 *
 * @author chanjarster
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, WxNewsMessage> {

  private List<WxNewsMessage.Item> articles = new ArrayList<>();

  public NewsBuilder addArticle(WxNewsMessage.Item... items) {
    Collections.addAll(this.articles, items);
    return this;
  }

  public NewsBuilder articles(List<WxNewsMessage.Item> articles){
    this.articles = articles;
    return this;
  }

  @Override
  public WxNewsMessage build() {
    WxNewsMessage m = new WxNewsMessage();
    m.addArticle(this.articles);
    setCommon(m);
    return m;
  }

}
