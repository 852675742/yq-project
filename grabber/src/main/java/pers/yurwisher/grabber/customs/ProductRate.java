package pers.yurwisher.grabber.customs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yq
 * @date 2018/08/22 14:09
 * @description 商品税率
 * @since V1.0.0
 */
@Data
@NoArgsConstructor
public class ProductRate implements Serializable {
    private static final long serialVersionUID = -1247866410746518161L;

    /**
     * 商品编码
     */
    private String productCode;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 进口关税率/低
     */
    private BigDecimal importTariffRateLow;
    /**
     * 进口关税率/普
     */
    private BigDecimal importTariffRateGeneral;
    /**
     * 增值税率
     */
    private BigDecimal addedValueRate;
    /**
     * 进口从价消费税税率
     */
    private BigDecimal consumptionRate;
    /**
     * 进口低从量消费税税率
     */
    private BigDecimal consumptionRateLow;
    /**
     * 监管条件
     */
    private String supervisionCondition;
}
