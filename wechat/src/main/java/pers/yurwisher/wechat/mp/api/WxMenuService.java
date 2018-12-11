package pers.yurwisher.wechat.mp.api;


import pers.yurwisher.wechat.mp.menu.in.WxMenu;
import pers.yurwisher.wechat.mp.menu.out.WxMpGetSelfMenuInfoResult;
import pers.yurwisher.wechat.mp.menu.out.WxMpMenu;

/**
 * @author yq
 * @date 2018/07/30 10:07
 * @description 微信菜单接口
 * @since V1.0.0
 */
public interface WxMenuService {

    /**
     * 创建自定义菜单url
     */
    String CREATE_POST_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";

    /**
     * 创建个性化菜单url
     */
    String CREATE_CONDITIONAL_POST_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional";

    /**
     * 获取菜单接口(包含自定义菜单和个性化菜单)
     */
    String GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";

    /**
     * 自定义菜单删除接口,调用此接口会删除默认菜单及全部个性化菜单
     */
    String DELETE_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";

    /**
     * 删除个性化菜单
     */
    String DELETE_CONDITIONAL_POST_URL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional";

    /**
     * 测试个性化菜单匹配结果
     */
    String TRY_MATCH_POST_URL = "https://api.weixin.qq.com/cgi-bin/menu/trymatch";

    /**
     * 获取自定义菜单配置接口
     */
    String GET_CURRENT_SELF_MENU_INFO_GET_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";

    /**
     * 菜单创建
     *
     * @param wxMenu 菜单
     * @return 菜单id
     */
    String create(WxMenu wxMenu);

    /**
     * 获取所有菜单,包含自定义菜单和个性化菜单
     *
     * @return 菜单
     */
    WxMpMenu get();

    /**
     * 自定义菜单删除接口 在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单
     */
    void delete();

    /**
     * menuId为菜单id，可以通过自定义菜单查询接口获取
     *
     * @param menuId 菜单id
     */
    void deleteConditional(String menuId);

    /**
     * 测试个性化菜单匹配结果
     *
     * @param userId user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
     * @return WxMenu
     */
    WxMenu tryMatch(String userId);

    /**
     * 获取自定义菜单配置
     *
     * @return 配置信息
     */
    WxMpGetSelfMenuInfoResult getCurrentSelfMenuInfo();
}
