package pers.yurwisher.grabber;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author yq
 * @date 2018/12/11 12:29
 * @description utils
 * @since V1.0.0
 */
@Data
public class JDomHelper {

    private static final Logger logger = LoggerFactory.getLogger(JDomHelper.class);

    /**
     * 请求超时时间,单位毫秒
     */
    private Integer timeOut ;
    /**
     * 客户代理
     */
    private String userAgent;

    private JDomHelper(Integer timeOut, String userAgent) {
        this.timeOut = timeOut;
        this.userAgent = userAgent;
    }

    public static JDomHelper getDefaultInstance(){
        return new JDomHelper(5 * 1000,"Mozilla");
    }

    /**
     * 获取请求资源文档对象
     * @param url 地址
     * @return Document
     */
    public Document getDocument(String url){
        Document doc;
        try {
            doc = Jsoup.connect(url).timeout(timeOut).userAgent(userAgent).get();
            return doc;
        } catch (IOException e) {
            throw new GrabException("connect url get document error",e);
        }
    }
}
