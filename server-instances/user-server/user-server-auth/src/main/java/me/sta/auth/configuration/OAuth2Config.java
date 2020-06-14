package me.sta.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceDetail userServiceDetail;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 将客户端的信息存储在内存中
        clients.inMemory()
                // 配置一个客户端
                .withClient("user-service")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                // 配置客户端的域
//                .accessTokenValiditySeconds(3600)
//                .refreshTokenValiditySeconds(864000)
                .scopes("all")
                 // 配置验证类型为refresh_token和password
                .authorizedGrantTypes("refresh_token", "password")
                // 配置token的过期时间为1h
                .accessTokenValiditySeconds(300);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();

        //允许 check_token 访问
        oauthServer.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置token的存储方式为JwtTokenStore
        endpoints
                .authenticationManager(authenticationManager)//认证管理服务，密码模式必须配置，还有userServiceDetail
//                .authorizationCodeServices(authorizationCodeServices)//授权码模式
                .tokenStore(tokenStore())
//                .tokenEnhancer(tokenEnhancer())
//                //在这里设置jwtAccessTokenConverter，发现是ok的，可以生成jwt令牌
//                .accessTokenConverter(jwtTokenEnhancer())
                .userDetailsService(userServiceDetail)//密码模式要配
         ;


        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(tokenEnhancer());
        enhancers.add(jwtTokenEnhancer());
        enhancerChain.setTokenEnhancers(enhancers);//将自定义Enhancer加入EnhancerChain的delegates数组中

        endpoints.tokenEnhancer(enhancerChain)//为什么不直接把jwtTokenEnhancer加在这个位置呢？
                .accessTokenConverter(jwtTokenEnhancer());

    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new JWTTokenEnhancer();
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 配置jks文件
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"), "fzp123".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
//        converter.setSigningKey("micosrv_signing_key");
        return converter;
    }
}