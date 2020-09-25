package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.usuario.Usuario;
import com.ibolsa.api.dto.usuario.UsuarioDTO;
import com.ibolsa.api.exceptions.DataIntegrityException;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.repositories.pg.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private PerfilService perfilService;

	public Optional<Usuario> find(Long id) {
		return repo.findById(id);
	}

	public List<Usuario> findAll(Boolean ativo) {
		return repo.findDistinctByAtivo(ativo);
	}

	public Usuario insert(Usuario usuario) {
		return repo.save(usuario);
	}

	public Usuario update(Usuario usuario) {
		if(usuario == null || usuario.getId() == null)
			throw new DataIntegrityException("Usuário id não pode ser nulo.");

		return repo.save(usuario);
	}

	public Usuario fromDTO(UsuarioDTO dto, Usuario usuario){
		usuario.setPerfilUsuario(perfilService.find(dto.getCodigoPerfil()).orElseThrow( () -> new ObjectNotFoundException("Perfil não encontrado! Código: " + dto.getCodigoPerfil())));

		return usuario;
	}
}
