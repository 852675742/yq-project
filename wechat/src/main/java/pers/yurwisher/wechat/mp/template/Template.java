package pers.yurwisher.wechat.mp.template;

import lombok.Data;

/**
 * @author yq
 * @date 2018/07/03 09:44
 * @description 微信消息模版
 * @since V1.0.0
 */
@Data
public class Template {

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 模板标题
     */
    private String title;

    /**
     * 模板所属行业的一级行业
     */
    private String primaryIndustry;

    /**
     * 	模板所属行业的二级行业
     */
    private String deputyIndustry;

    /**
     * 	模板内容
     */
    private String content;

    /**
     *  模板示例
     */
    private String example;

}
