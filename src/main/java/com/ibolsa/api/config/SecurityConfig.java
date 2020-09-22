package com.ibolsa.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private Environment env;
	
////	@Autowired
////	private JWTUtil jwtUtil;
////
////	@Autowired
////	private UserDetailsService userDetailsService;
////
////	@Autowired
////	private UsuarioService usuarioService;
//
//	private static final String[] PUBLIC_MATCHERS = {
//		"/usuarios/{\\\\d+}/avatar"
//	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
//		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil, usuarioService));
//		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService, usuarioService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception{
////		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("a").password("a").roles("USER");
	}
	
//	@Bean
//	  CorsConfigurationSource corsConfigurationSource() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
//	    corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
//	    source.registerCorsConfiguration("/**", corsConfig);
//	    return source;
//	  }
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
	    config.setAllowCredentials(true);
	    config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

	    final UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
	    configSource.registerCorsConfiguration("/**", config);

	    return configSource;
	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
