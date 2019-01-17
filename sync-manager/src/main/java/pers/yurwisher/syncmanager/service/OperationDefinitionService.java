package pers.yurwisher.syncmanager.service;

import com.alibaba.fastjson.JSONObject;
import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.entity.OperationDefinition;

/**
 * @author yq
 * @date 2019/01/15 17:05
 * @description 操作定义
 * @since V1.0.0
 */
public interface OperationDefinitionService {

    /**
     * 获取操作定义
     * @param id 主键ID
     * @return OperationDefinition
     */
    OperationDefinition getOperationDefinition(Integer id);

    /**
     * 执行接口
     * @param record 待处理消息
     * @param operationDefinition 操作定义
     * @param json json参数
     * @return 接口执行后返回结果
     * @throws ReflectiveOperationException 反射异常
     */
    Object invoke(MessageRecord record,OperationDefinition operationDefinition, JSONObject json) throws ReflectiveOperationException;
}
