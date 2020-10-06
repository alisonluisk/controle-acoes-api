package com.ibolsa.api.services;

import com.ibolsa.api.config.UserSS;
import com.ibolsa.api.domain.pg.usuario.Usuario;
import com.ibolsa.api.exceptions.AcessoNegadoException;
import com.ibolsa.api.repositories.pg.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, AcessoNegadoException {
		Optional<Usuario> usuario = repo.findByEmailColaborador(email);
		if(usuario.isEmpty()) 
			throw new UsernameNotFoundException("Usuário não localizado.");
		if(!usuario.get().isAtivo())
			throw new AcessoNegadoException("Usuário não está ativo.");

		return new UserSS(usuario.get());
	}

}
