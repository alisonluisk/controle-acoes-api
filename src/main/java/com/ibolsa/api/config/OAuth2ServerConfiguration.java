package com.ibolsa.api.config;

import com.ibolsa.api.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore = new InMemoryTokenStore();

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private WebResponseExceptionTranslator oauth2ResponseExceptionTranslator;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfiguration){
        endpointsConfiguration.tokenStore(this.tokenStore)
                .exceptionTranslator(oauth2ResponseExceptionTranslator)
                .authenticationManager(this.authenticationManager)
                .userDetailsService(userDetailsService);
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws  Exception{
        clients.inMemory()
                .withClient("iBolsaSAWeb")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("bar", "read", "write")
                .refreshTokenValiditySeconds(86400)
                .accessTokenValiditySeconds(3600)
                .resourceIds("restservice")
                .secret(new BCryptPasswordEncoder().encode("iBolSaSA2020"));
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenService(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();

        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(this.tokenStore);
        return tokenServices;
    }
 }