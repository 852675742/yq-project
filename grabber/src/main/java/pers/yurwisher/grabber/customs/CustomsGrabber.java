package pers.yurwisher.grabber.customs;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.grabber.GrabException;
import pers.yurwisher.grabber.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author yq
 * @date 2018/08/22 08:52
 * @description 海关API
 * @since V1.0.0
 */
public class CustomsGrabber {

    private static final Logger logger = LoggerFactory.getLogger(CustomsGrabber.class);

    /**
     * 超时时间
     */
    private Integer timeOut = 3 * 1000;

    public CustomsGrabber(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public CustomsGrabber() {
    }

    /**
     * 客户端模拟
     */
    private static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";
    /**
     * 客户端
     */
    private static final String USER_AGENT = "User-Agent";
    private static final String COOKIE_HEADER = "Cookie";
    private static final String REFERER_HEADER = "Referer";

    /**
     * 通关状态请求地址
     */
    private static final String GATEWAY_STATE_QUERY_URL = "http://www.haiguan.info/OnLineSearch/Gateway/weixin/weixinGatewayStateResult.aspx";
    /**
     * 通关状态页面地址
     */
    private static final String GATEWAY_STATE_URL = "http://www.haiguan.info/OnLineSearch/Gateway/weixin/weixinGatewayState.html";

    /**
     * 商品税率查询页面地址
     */
    private static final String PRODUCT_RATE_URL = "http://www.haiguan.info/OnLineSearch/Gateway/weixin/weixinProductRate.html";
    /**
     * 商品税率请求地址
     */
    private static final String PRODUCT_RATE_QUERY_URL = "http://www.haiguan.info/OnLineSearch/Gateway/weixin/weixinProductRateResult.aspx";
    /**
     * 商品归类查询页面地址
     */
    private static final String PRODUCT_CLASS_URL = "http://www.haiguan.info/OnLineSearch/Gateway/weixin/weixinproductClass.html";
    /**
     * 商品归类请求地址
     */
    private static final String PRODUCT_CLASS_QUERY_URL = "http://www.haiguan.info/OnLineSearch/Gateway/weixin/weixinProductClassResult.aspx";


    /**
     * 查询通关状态
     *
     * @param stateCode 报关单号 18位
     */
    public GatewayState queryGatewayState(String stateCode) {
        //查询后得到对象
        Document document = getDocument(GATEWAY_STATE_QUERY_URL, GATEWAY_STATE_URL, new HashMap<String, String>() {
            {
                put("stateCode", stateCode);
            }
        });
        //数据列td
        Elements tds = document.getElementById("containter").select("td.td_white");
        logger.info("数据列:{}条", Utils.size(tds));
        if (Utils.isNotEmpty(tds)) {
            GatewayState gatewayState = new GatewayState();
            gatewayState.setDeclarationNumber(Utils.null2EmptyWithTrimNew(tds.get(0).html()));
            gatewayState.setStatus(Utils.null2EmptyWithTrimNew(tds.get(1).html()));
            return gatewayState;
        }
        return null;
    }

    /**
     * 查询商品税率
     *
     * @param productCode 商品编码,即税号 + 附号(不存在则补00)
     */
    public ProductRate queryProductRate(String productCode) {
        //查询后得到对象
        Document document = getDocument(PRODUCT_RATE_QUERY_URL, PRODUCT_RATE_URL, new HashMap<String, String>() {
            {
                put("productCode", productCode);
            }
        });
        Element containter = document.getElementById("containter");
        if (containter != null) {
            //数据列td
            Elements tds = containter.select("td.td_white");
            logger.info("数据列:{}条", Utils.size(tds));
            if (Utils.isNotEmpty(tds)) {
                ProductRate rate = new ProductRate();
                //商品编码
                rate.setProductCode(tds.get(0).html());
                //商品名称
                rate.setProductName(tds.get(1).html());
                //进口关税率/低
                rate.setImportTariffRateLow(Utils.string2BigDecimal(tds.get(2).html()));
                //进口关税率/普
                rate.setImportTariffRateGeneral(Utils.string2BigDecimal(tds.get(3).html()));
                //增值税率
                rate.setAddedValueRate(Utils.string2BigDecimal(tds.get(4).html()));
                //进口从价消费税税率
                rate.setConsumptionRate(Utils.string2BigDecimal(tds.get(5).html()));
                //进口低从量消费税税率
                rate.setConsumptionRateLow(Utils.string2BigDecimal(tds.get(6).html()));
                //监管条件
                rate.setSupervisionCondition(tds.get(7).html());
                return rate;
            }
        }
        return null;
    }

    /**
     * 商品归类查询
     *
     * @param productName 商品名称
     * @return 归类信息
     */
    public List<ProductClass> queryProductClass(String productName) {
        //查询后得到对象
        Document document = getDocument(PRODUCT_CLASS_QUERY_URL, PRODUCT_CLASS_URL, new HashMap<String, String>() {
            {
                put("productName", productName);
            }
        });
        Element containter = document.getElementById("containter");
        if (containter != null) {
            Elements trs = containter.getElementsByTag("tr");
            int size = Utils.size(trs);
            logger.info("商品归类信息返回tr:{}", size);
            //第一行为表头
            if (Utils.isNotEmpty(trs) && trs.size() > 1) {
                List<ProductClass> list = new ArrayList<>(size);
                for (int i = 1; i < trs.size(); i++) {
                    Element tr = trs.get(i);
                    ProductClass productClass = new ProductClass();
                    productClass.setDeclarationNumber(tr.child(0).html());
                    productClass.setProductName(tr.child(1).html());
                    productClass.setDeclareElements(tr.child(2).html());
                    list.add(productClass);
                }
                return list;
            }
        }
        return null;
    }

    /**
     * 获取抓取后的文档对象
     *
     * @param queryUrl 查询接口地址
     * @param url      REFERER地址
     * @param params   参数
     * @return 文档对象
     */
    private Document getDocument(String queryUrl, String url, Map<String, String> params) {
        String verifyCode = random4();
        //发起查询请求
        Connection queryCon = Jsoup.connect(queryUrl);
        queryCon.header(USER_AGENT, USER_AGENT_VALUE)
                .header(COOKIE_HEADER, "CheckCode=" + verifyCode)
                .header(REFERER_HEADER, url);
        // 设置cookie和post上面的map数据
        Connection.Response queryRs = null;
        try {
            queryRs = queryCon.ignoreContentType(true)
                    .followRedirects(true)
                    .method(Connection.Method.POST)
                    .data(params)
                    .data("verifyCode", verifyCode)
                    .timeout(timeOut).execute();
        } catch (IOException e) {
            throw new GrabException("connect url get document error", e);
        }
        //查询后得到对象
        return Jsoup.parse(queryRs.body());
    }

    /**
     * 随机四位数
     *
     * @return string
     */
    private String random4() {
        return Integer.toString((int) ((Math.random() * 9 + 1) * 1000));
    }

    public static void main(String[] args) throws Exception {
//        CustomsGrabber customsGrabber = new CustomsGrabber();
//        GatewayState state = customsGrabber.queryGatewayState("534520181451298325");
//        System.out.println(state.getDeclarationNumber());
//        queryGatewayState("534520181451298325");
//        queryProductRate("9001909090");
//        queryProductClass("集成电路(调节功能非加密)");
    }

}
