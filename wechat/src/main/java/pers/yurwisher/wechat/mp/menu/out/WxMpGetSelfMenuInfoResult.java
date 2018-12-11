package pers.yurwisher.wechat.mp.menu.out;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义菜单配置 接口返回
 * @author yq
 */
@Data
public class WxMpGetSelfMenuInfoResult implements Serializable {

    private static final long serialVersionUID = 183007534888605608L;

    /**
     * 菜单信息
     */
    @JSONField(name = "selfmenu_info")
    private WxMpSelfMenuInfo selfMenuInfo;

    /**
     * 	菜单是否开启，0代表未开启，1代表开启
     */
    @JSONField(name = "is_menu_open")
    private Integer isMenuOpen;

}
