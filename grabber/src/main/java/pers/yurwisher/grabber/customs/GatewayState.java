package pers.yurwisher.grabber.customs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/08/22 13:38
 * @description 报关状态
 * @since V1.0.0
 */
@Data
@NoArgsConstructor
public class GatewayState implements Serializable {
    private static final long serialVersionUID = 8864480701189934606L;

    /**
     * 报关单号
     */
    private String declarationNumber;
    /**
     * 报关状态
     */
    private String status;
}
