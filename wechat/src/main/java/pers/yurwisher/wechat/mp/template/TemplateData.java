package pers.yurwisher.wechat.mp.template;


import lombok.Data;

/**
 * @author yq
 * @date 2018/07/03 09:44
 * @description 模版消息通用封装
 * @since V1.0.0
 */
@Data
public class TemplateData {
    /**key*/
   private String key ;
   /**值*/
   private String value ;
   /**颜色*/
   private String color ;

    public TemplateData(String key, String value, String color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }


    public TemplateData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public TemplateData() {
    }

}
