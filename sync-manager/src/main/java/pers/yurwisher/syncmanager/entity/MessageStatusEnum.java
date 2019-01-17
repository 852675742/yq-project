package pers.yurwisher.syncmanager.entity;

/**
 * @author yq
 * @date 2018/04/19 17:17
 * @description 日志状态枚举
 * @since V1.0.0
 */
public enum MessageStatusEnum {

    NEW("初始化"),
    SUCCEED("成功"),
    EXCEPTION("异常"),
    ERROR("错误");

    private String description;

    MessageStatusEnum(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description ;
    }
}
