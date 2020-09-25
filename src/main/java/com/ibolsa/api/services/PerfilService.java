package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.usuario.PerfilUsuario;
import com.ibolsa.api.dto.usuario.PerfilDTO;
import com.ibolsa.api.exceptions.DataIntegrityException;
import com.ibolsa.api.repositories.pg.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository repo;

	public Optional<PerfilUsuario> find(Long id) {
		return repo.findById(id);
	}

	public List<PerfilUsuario> findAll() {
		return repo.findAll();
	}

	public PerfilUsuario insert(PerfilUsuario perfilUsuario) {
		return repo.save(perfilUsuario);
	}

	public PerfilUsuario update(PerfilUsuario perfilUsuario) {
		if(perfilUsuario == null || perfilUsuario.getId() == null)
			throw new DataIntegrityException("Perfil id não pode ser nulo.");

		return repo.save(perfilUsuario);
	}

	public PerfilUsuario fromDTO(PerfilDTO dto, PerfilUsuario perfilUsuario){
		perfilUsuario.setDescricao(dto.getDescricao());
		perfilUsuario.setNome(dto.getNome());

		//TODO VERIFICAR ISSO
//		perfil.setPerfilAcesso(dto.getPerfilAcesso());

		return perfilUsuario;
	}
}
