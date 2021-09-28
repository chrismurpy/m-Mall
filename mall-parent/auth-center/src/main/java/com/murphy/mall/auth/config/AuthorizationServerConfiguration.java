package com.murphy.mall.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * 认证服务器 配置
 *
 * @author murphy
 * @since 2021/9/28 4:36 下午
 */
@Configuration
// 开启授权服务器 - 配置
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 令牌访问端点的权限
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 配置 /oauth/token_key端点，没有权限也可以访问
                .tokenKeyAccess("permitAll()")
                // 配置 /oauth/check_token 验证端点，认证权限访问
//                .checkTokenAccess("isAuthenticated()")
                // 不用认证亦可访问
                .checkTokenAccess("permitAll()")
                // 允许表单认证
                .allowFormAuthenticationForClients();
    }

    /**
     * 定义从数据库 oauth_client_details 表中获取客户端信息
     * @return
     */
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置客户端信息 - 读取 oauth_client_details 表中的客户端信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 采用JWT存储令牌
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 采用非对称式加密算法 - 私钥颁发令牌，公钥解析令牌
     * @return
     */
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("kaikeba.jks"),"kaikeba".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 密钥别名
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("kaikeba"));
        return converter;
    }

    /**
     * 配置令牌的产生方式 - 存储以及tokenService的基本信息
     * JWT存储令牌 + 非对称加密 + 认证服务器
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter()).authenticationManager(authenticationManager);

        // 配置tokenService的基本参数
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(endpoints.getTokenStore());
        defaultTokenServices.setSupportRefreshToken(false);
        defaultTokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        defaultTokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        // 30天 - 默认时效
        defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
        endpoints.tokenServices(defaultTokenServices);
    }
}
