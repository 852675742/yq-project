package pers.yurwisher.wechat.mp;

import pers.yurwisher.wechat.mp.template.MpIndustry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yq
 * @date 2018/07/06 19:13
 * @description 公众号固定数据
 * @since V1.0.0
 */
public class MpData {

    /**行业代码 及名称*/
    public static final List<MpIndustry.Industry> INDUSTRY_LIST = new ArrayList<MpIndustry.Industry>(){{
        add(new MpIndustry.Industry("IT科技","互联网/电子商务","1"));
        add(new MpIndustry.Industry("IT科技","IT软件与服务","2"));
        add(new MpIndustry.Industry("IT科技","IT硬件与设备","3"));
        add(new MpIndustry.Industry("IT科技","电子技术","4"));
        add(new MpIndustry.Industry("IT科技","通信与运营商","5"));
        add(new MpIndustry.Industry("IT科技","网络游戏","6"));
        add(new MpIndustry.Industry("金融业","银行","7"));
        add(new MpIndustry.Industry("金融业","基金理财信托","8"));
        add(new MpIndustry.Industry("金融业","保险","9"));
        add(new MpIndustry.Industry("餐饮","餐饮","10"));
        add(new MpIndustry.Industry("酒店旅游","酒店","11"));
        add(new MpIndustry.Industry("酒店旅游","旅游","12"));
        add(new MpIndustry.Industry("运输与仓储","快递","13"));
        add(new MpIndustry.Industry("运输与仓储","物流","14"));
        add(new MpIndustry.Industry("运输与仓储","仓储","15"));
        add(new MpIndustry.Industry("教育","培训","16"));
        add(new MpIndustry.Industry("教育","院校","17"));
        add(new MpIndustry.Industry("政府与公共事业","学术科研","18"));
        add(new MpIndustry.Industry("政府与公共事业","交警","19"));
        add(new MpIndustry.Industry("政府与公共事业","博物馆","20"));
        add(new MpIndustry.Industry("政府与公共事业","公共事业非盈利机构","21"));
        add(new MpIndustry.Industry("医药护理","医药医疗","22"));
        add(new MpIndustry.Industry("医药护理","护理美容","23"));
        add(new MpIndustry.Industry("医药护理","保健与卫生","24"));
        add(new MpIndustry.Industry("交通工具","汽车相关","25"));
        add(new MpIndustry.Industry("交通工具","摩托车相关","26"));
        add(new MpIndustry.Industry("交通工具","火车相关","27"));
        add(new MpIndustry.Industry("交通工具","飞机相关","28"));
        add(new MpIndustry.Industry("房地产","建筑","29"));
        add(new MpIndustry.Industry("房地产","物业","30"));
        add(new MpIndustry.Industry("消费品","消费品","31"));
        add(new MpIndustry.Industry("商业服务","法律","32"));
        add(new MpIndustry.Industry("商业服务","会展","33"));
        add(new MpIndustry.Industry("商业服务","中介服务","34"));
        add(new MpIndustry.Industry("商业服务","认证","35"));
        add(new MpIndustry.Industry("商业服务","审计","36"));
        add(new MpIndustry.Industry("文体娱乐","传媒","37"));
        add(new MpIndustry.Industry("文体娱乐","体育","38"));
        add(new MpIndustry.Industry("文体娱乐","娱乐休闲","39"));
        add(new MpIndustry.Industry("印刷","印刷","40"));
        add(new MpIndustry.Industry("其它","其它","41"));
    }};

}
