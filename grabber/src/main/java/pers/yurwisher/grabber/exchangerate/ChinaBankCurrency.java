package pers.yurwisher.grabber.exchangerate;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/12/11 14:47
 * @description 中行币制
 * @since V1.0.0
 */
@Data
public class ChinaBankCurrency implements Serializable {
    private static final long serialVersionUID = 8994150974477856945L;

    /**
     * 编码
     */
    String code ;
    /**
     * 名称
     */
    String name ;
}
