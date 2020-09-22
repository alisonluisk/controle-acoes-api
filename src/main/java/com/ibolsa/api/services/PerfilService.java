package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.usuario.Perfil;
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

	public Optional<Perfil> find(Long id) {
		return repo.findById(id);
	}

	public List<Perfil> findAll() {
		return repo.findAll();
	}

	public Perfil insert(Perfil perfil) {
		return repo.save(perfil);
	}

	public Perfil update(Perfil perfil) {
		if(perfil == null || perfil.getId() == null)
			throw new DataIntegrityException("Perfil id não pode ser nulo.");

		return repo.save(perfil);
	}

	public Perfil fromDTO(PerfilDTO dto, Perfil perfil){
		perfil.setDescricao(dto.getDescricao());
		perfil.setNome(dto.getNome());

		//TODO VERIFICAR ISSO
//		perfil.setPerfilAcesso(dto.getPerfilAcesso());

		return perfil;
	}
}
