package pers.yurwisher.grabber.customs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/08/22 14:36
 * @description 商品归类信息
 * @since V1.0.0
 */
@Data
@NoArgsConstructor
public class ProductClass implements Serializable {
    private static final long serialVersionUID = -595347602659814678L;

    /**
     * 商品税号
     */
    private String declarationNumber;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 申报要素
     */
    private String declareElements;
}
