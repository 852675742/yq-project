package pers.yurwisher.wechat.mp.menu.out;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import pers.yurwisher.wechat.mp.menu.in.WxMenuButton;
import pers.yurwisher.wechat.mp.menu.in.WxMenuRule;

import java.io.Serializable;
import java.util.List;

/**
 * 在设置了个性化菜单后，使用本自定义菜单查询接口可以获取默认菜单和全部个性化菜单信息
 * 获取菜单接口返回结构
 * @author yq
 */
@Data
public class WxMpMenu implements Serializable {

    private static final long serialVersionUID = 5749769685733478277L;
    /**
     * 自定义菜单
     */
    private WxMpConditionalMenu menu;

    /**
     * 个性化菜单
     */
    @JSONField(name = "conditionalmenu")
    private List<WxMpConditionalMenu> conditionalMenu;

    @Data
    public static class WxMpConditionalMenu implements Serializable {

        private static final long serialVersionUID = -8168297147323990559L;

        /**
         * 菜单集合
         */
        private List<WxMenuButton> button;

        /**
         * 菜单匹配规则
         */
        @JSONField(name = "matchrule")
        private WxMenuRule matchRule;

        /**
         * 菜单ID
         */
        @JSONField(name = "menuid")
        private String menuId;

    }

}
