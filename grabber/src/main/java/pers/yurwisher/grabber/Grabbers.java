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

    private static class GrabbersHolder{
        private static final Grabbers INSTANCE = new Grabbers();
    }

    public static Grabbers getInstance(){
        return GrabbersHolder.INSTANCE;
    }

    private CustomsGrabber customsGrabber;
    private ExchangeRateGrabber exchangeRateGrabber;
    private Kuaidi100Grabber kuaidi100Grabber;

    private Grabbers(){
        this.customsGrabber = new CustomsGrabber();
        this.exchangeRateGrabber = new ExchangeRateGrabber(JDomHelper.getDefaultInstance());
        this.kuaidi100Grabber = new Kuaidi100Grabber(new HttpClientHelper());
    }

    public CustomsGrabber getCustomsGrabber() {
        return customsGrabber;
    }

    public ExchangeRateGrabber getExchangeRateGrabber() {
        return exchangeRateGrabber;
    }

    public Kuaidi100Grabber getKuaidi100Grabber() {
        return kuaidi100Grabber;
    }
}
