package pers.yurwisher.tokenstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.yurwisher.token.TokenHelper;
import pers.yurwisher.token.TokenService;

/**
 * @author yq
 * @date 2018/12/06 14:56
 * @description token自动配置
 * @since V1.0.0
 */
@Configuration
@EnableConfigurationProperties(value = TokenConfig.class)
@ConditionalOnProperty(prefix = "yurwisher.token", value = "enable", matchIfMissing = true)
public class TokenAutoConfiguration {

    @Autowired
    private TokenConfig tokenConfig;

    /**
     * ConditionalOnClass : classpath下存在此类
     * ConditionalOnMissingBean :spring上下文不存在此bean
     */
    @Bean
    @ConditionalOnClass(value = TokenHelper.class)
    @ConditionalOnMissingBean(TokenHelper.class)
    public TokenHelper tokenHelper(){
        TokenHelper tokenHelper =  new TokenHelper(tokenConfig.getSecret(),
                tokenConfig.getExpireTime(),tokenConfig.getIssuer(),
                tokenConfig.getSignatureAlgorithm(),tokenConfig.getAudience());
        tokenHelper.setCustomTokenClass(tokenConfig.getCustomTokenClass());
        return tokenHelper;
    }

    @Bean
    @ConditionalOnClass(value = TokenService.class)
    @ConditionalOnMissingBean(TokenService.class)
    public TokenService tokenService(TokenHelper tokenHelper){
        return new TokenService(tokenHelper);
    }


}
