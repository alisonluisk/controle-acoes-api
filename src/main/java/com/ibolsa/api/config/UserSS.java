package com.ibolsa.api.config;

import com.ibolsa.api.domain.pg.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {
	}
	
	public UserSS(Usuario usuario) {
		super();
		this.usuario = usuario;
		this.authorities = usuario.getPerfilUsuario().getAcessos().stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Long getId() {
		return usuario.getId();
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		if(usuario.getColaborador() != null){
			return usuario.getColaborador().getEmail();
		}
		return usuario.getUsuario();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
