package pers.yurwisher.wechatstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.yurwisher.wechat.mp.api.MpService;
import pers.yurwisher.wechat.mp.api.impl.DefaultWxConfigRepository;
import pers.yurwisher.wechat.mp.api.impl.MpServiceImpl;

/**
 * @author yq
 * @date 2018/12/06 14:56
 * @description
 * @since V1.0.0
 */
@Configuration
//启动配置文件，value用来指定我们要启用的配置类
@EnableConfigurationProperties(value = WeChatConfig.class)
//表示当classPath下存在HelloService.class文件时改配置文件类才有效
@ConditionalOnClass(value = MpService.class)
//表示只有我们的配置文件是否配置了以yq.weChat为前缀的资源项值，并且在该资源项值为enable，如果没有配置我们默认设置为enable
@ConditionalOnProperty(prefix = "yq.weChat", value = "enable", matchIfMissing = true)
public class WeChatAutoConfiguration {

    @Autowired
    private WeChatConfig weChatConfig;

    @Bean
    @ConditionalOnMissingBean(MpService.class)
    public MpService mpService(){
        DefaultWxConfigRepository repository = new DefaultWxConfigRepository();
        repository.setAppId(weChatConfig.getAppId());
        repository.setSecret(weChatConfig.getSecret());
        repository.setToken(weChatConfig.getServerToken());
        repository.setAesKey(weChatConfig.getAesKey());
        MpService mpService = new MpServiceImpl(repository);
        repository.setMpService(mpService);
        return mpService;
    }

}
