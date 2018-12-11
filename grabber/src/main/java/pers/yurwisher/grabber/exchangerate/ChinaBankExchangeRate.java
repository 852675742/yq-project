package pers.yurwisher.grabber.exchangerate;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author yq
 * @date 2018/12/11 12:21
 * @description 中行汇率
 * @since V1.0.0
 */
@Data
public class ChinaBankExchangeRate implements Serializable {

    private static final long serialVersionUID = 2632786685443893870L;
    /**
     * 中行折算价
     */
    private BigDecimal bankConvertRate;

    /**
     * 现汇买入价
     */
    private BigDecimal buyingRate;

    /**
     * 现钞买入价
     */
    private BigDecimal cashBuyingRate;

    /**
     * 现钞卖出价
     */
    private BigDecimal cashSellingRate;

    /**
     * 币制名称
     */
    private String currency;

    /**
     * 币制编码
     */
    private String currencyCode;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 现汇卖出价
     */
    private BigDecimal sellingRate;
}
