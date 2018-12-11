package pers.yurwisher.wechat.mp.menu.in;


import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yurwisher.wechat.common.base.JsonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信菜单（公众号和企业号共用的）.
 * 个性化菜单的更新是会被覆盖的。
 * 例如公众号先后发布了默认菜单，个性化菜单1，个性化菜单2，个性化菜单3。那么当用户进入公众号页面时，
 * 将从个性化菜单3开始匹配，如果个性化菜单3匹配成功，则直接返回个性化菜单3，否则继续尝试匹配个性化菜单2，直到成功匹配到一个菜单。
 * 根据上述匹配规则，为了避免菜单生效时间的混淆，决定不予提供个性化菜单编辑API，开发者需要更新菜单时，需将完整配置重新发布一轮。
 * @author yq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMenu extends JsonBean {

  private static final long serialVersionUID = -8677609151541869599L;
  /**
   * 菜单
   */
  private List<WxMenuButton> button = new ArrayList<>();

  /**
   * 菜单匹配规则,个性化菜单必填
   */
  private WxMenuRule wxMenuRule;

}
