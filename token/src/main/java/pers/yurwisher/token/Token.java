package pers.yurwisher.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;


/**
 * @author yq
 * @date 2018/12/10 14:00
 * @description token凭证
 * @since V1.0.0
 */
@Data
public class Token{

    /**
     * 转json对象
     * @return JSONObject
     */
    public JSONObject toJSON(){
       return (JSONObject) JSON.toJSON(this);
    }

}
