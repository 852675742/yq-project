package pers.yurwisher.wechat.mp.kf.builder;

import pers.yurwisher.wechat.mp.kf.message.KefuNewsMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yq
 * @date 2018/07/31 15:02
 * @description 客服图文消息builder
 * @since V1.0.0
 */
public class KefuNewsBuilder extends BaseKefuBuilder<KefuNewsBuilder, KefuNewsMessage> {

    private List<KefuNewsMessage.KefuNews.Article> articles = new ArrayList<>();

    public KefuNewsBuilder addArticle(KefuNewsMessage.KefuNews.Article...articles) {
        Collections.addAll(this.articles, articles);
        return this;
    }

    public KefuNewsBuilder articles(List<KefuNewsMessage.KefuNews.Article> articles) {
        this.articles = articles;
        return this;
    }

    @Override
    public KefuNewsMessage build() {
        KefuNewsMessage message = new KefuNewsMessage();
        super.setToUser(message);
        message.getNews().getArticles().addAll(articles);
        return message;
    }
}
