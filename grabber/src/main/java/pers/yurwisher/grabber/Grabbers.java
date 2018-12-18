package pers.yurwisher.grabber;

import pers.yurwisher.grabber.customs.CustomsGrabber;
import pers.yurwisher.grabber.exchangerate.ExchangeRateGrabber;
import pers.yurwisher.grabber.express.Kuaidi100Grabber;

/**
 * @author yq
 * @date 2018/12/11 17:09
 * @description grabbers
 * @since V1.0.0
 */
public class Grabbers {

    private Grabbers(){}

    private static CustomsGrabber customsGrabber;
    private static ExchangeRateGrabber exchangeRateGrabber;
    private static Kuaidi100Grabber kuaidi100Grabber;

    static {
        customsGrabber = new CustomsGrabber();
        exchangeRateGrabber = new ExchangeRateGrabber(JDomHelper.getDefaultInstance());
        kuaidi100Grabber = new Kuaidi100Grabber(new HttpClientHelper());
    }

    public static CustomsGrabber getCustomsGrabber() {
        return customsGrabber;
    }

    public static ExchangeRateGrabber getExchangeRateGrabber() {
        return exchangeRateGrabber;
    }

    public static Kuaidi100Grabber getKuaidi100Grabber() {
        return kuaidi100Grabber;
    }

}
