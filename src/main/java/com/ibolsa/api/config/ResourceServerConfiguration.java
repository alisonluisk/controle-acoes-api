package com.ibolsa.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    @Autowired
    private WebResponseExceptionTranslator oauth2ResponseExceptionTranslator;

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer){
        OAuth2AccessDeniedHandler auth2AccessDeniedHandler = new OAuth2AccessDeniedHandler();
        auth2AccessDeniedHandler.setExceptionTranslator(oauth2ResponseExceptionTranslator);
        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(oauth2ResponseExceptionTranslator);
        configurer.resourceId("restservice").accessDeniedHandler(auth2AccessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and().authorizeRequests()
                .anyRequest().fullyAuthenticated();
    }
}
