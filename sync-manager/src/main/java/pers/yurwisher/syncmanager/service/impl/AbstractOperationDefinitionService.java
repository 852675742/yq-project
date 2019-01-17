package pers.yurwisher.syncmanager.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pers.yurwisher.syncmanager.entity.MessageStatusEnum;
import pers.yurwisher.syncmanager.entity.MessageRecord;
import pers.yurwisher.syncmanager.entity.OperationDefinition;
import pers.yurwisher.syncmanager.exception.SyncException;
import pers.yurwisher.syncmanager.service.MessageRecordService;
import pers.yurwisher.syncmanager.service.OperationDefinitionService;
import pers.yurwisher.syncmanager.service.OperationLogService;

import java.lang.reflect.Method;

/**
 * @author yq
 * @date 2019/01/15 17:06
 * @description 操作定义
 * @since V1.0.0
 */
public abstract class AbstractOperationDefinitionService implements OperationDefinitionService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractOperationDefinitionService.class);

    private MessageRecordService messageRecordService ;
    private OperationLogService operationLogService ;
    private ApplicationContext applicationContext;

    public AbstractOperationDefinitionService(MessageRecordService messageRecordService,
                                              OperationLogService operationLogService,
                                              ApplicationContext applicationContext) {
        this.messageRecordService = messageRecordService;
        this.operationLogService = operationLogService;
        this.applicationContext = applicationContext;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object invoke(MessageRecord message,OperationDefinition definition, JSONObject params) throws ReflectiveOperationException{
        //执行
        Object object;
        if(definition != null){
            String serviceName = definition.getServiceName();
            String methodName = definition.getMethodName();
            Method method =  getMethod(serviceName,methodName,JSONObject.class);
            Object bean = applicationContext.getBean(getBeanNameInSpring(serviceName));
            if(method != null && bean != null){
                object = method.invoke(bean,params);
            }else {
                logger.info("执行对象不存在");
                throw new SyncException("执行对象不存在");
            }
        }else {
            throw new SyncException("执行接口不存在");
        }
        //更新状态
        messageRecordService.updateStatus(message.getId(), MessageStatusEnum.SUCCEED);
        //新增日志
        operationLogService.saveOperationLog(message,MessageStatusEnum.SUCCEED,null);
        return object;
    }

    /**
     * @param className 全路径类名
     * @param methodName 方法名
     */
    @SuppressWarnings("unchecked")
    private Method getMethod(String className,String methodName,Class<?>... parameterTypes) throws ClassNotFoundException, NoSuchMethodException {
        if(!StringUtils.isEmpty(className) && !StringUtils.isEmpty(methodName)){
            Class clazz =  Class.forName(className);
            return  clazz.getMethod(methodName,parameterTypes);
        }
        return null;
    }

    /**
     * 取类在spring容器中的bean name
     */
    private String getBeanNameInSpring(String className){
        return firstLetterUpperOrLower(className.substring(className.lastIndexOf(".") + 1),true);
    }

    /**
     * 字符串首字母大写或小写
     */
    private String firstLetterUpperOrLower(String str, boolean isLowerCase) {
        if (str != null && !str.isEmpty()) {
            if (str.length() == 1) {
                return isLowerCase ? str.toLowerCase() : str.toUpperCase();
            } else {
                String first = str.substring(0, 1).toLowerCase();
                first = isLowerCase ? first.toLowerCase() : first.toUpperCase();
                return (first + str.substring(1));
            }
        }
        return str;
    }

}
