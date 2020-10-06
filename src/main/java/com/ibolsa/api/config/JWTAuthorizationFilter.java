package com.ibolsa.api.config;

import com.ibolsa.api.exceptions.AcessoNegadoException;
import com.ibolsa.api.exceptions.AuthorizationException;
import com.ibolsa.api.services.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService userDetailsService, UsuarioService usuarioService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

		try {
			String header = request.getHeader("Authorization");
			if(header != null && header.contains("Bearer ")) {
				header = header.substring(7);
				UsernamePasswordAuthenticationToken auth = getAuthentication(header);
				if(auth == null) 	
					onUnsuccessfulAuthentication(request, response,new AuthorizationException("Token inválido ou vencido"));
				else {
					SecurityContextHolder.getContext().setAuthentication(auth);
					chain.doFilter(request, response);					
				}

			}else {
				chain.doFilter(request, response);
			}						
		}catch(RuntimeException re) {
			onUnsuccessfulAuthentication(request, response,new AcessoNegadoException(re.getMessage()));
		}
	}
	

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
	
	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json"); 
        response.getWriter().append(json(request.getRequestURI(), failed));
	}
	
    private String json(String path, AuthenticationException failed) {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", "
            + "\"status\": 401, "
            + "\"error\": \"Acesso negado\", "
            + "\"message\": \"" + failed.getMessage() + "\", "
            + "\"path\": \"" + path + "\"}";
    }
}
