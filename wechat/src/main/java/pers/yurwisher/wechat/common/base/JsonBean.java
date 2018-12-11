package pers.yurwisher.wechat.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * @author yq
 * @date 2018/07/30 10:01
 * @description 需要转json的基类
 * @since V1.0.0
 */
public class JsonBean implements Serializable {

    private static final long serialVersionUID = 1935370093546265530L;

    public String toJSON(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
