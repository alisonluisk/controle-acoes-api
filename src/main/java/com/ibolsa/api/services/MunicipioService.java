package com.ibolsa.api.services;

import java.util.List;
import java.util.Optional;

import com.ibolsa.api.domain.pg.municipio.Estado;
import com.ibolsa.api.domain.pg.municipio.Municipio;
import com.ibolsa.api.repositories.pg.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipioService {
	
	@Autowired
	private MunicipioRepository repoMunicipio;
	
	public Optional<Municipio> find(Long id) {
		return repoMunicipio.findById(id);
	}
	
	public List<Municipio> findAll() {
		return repoMunicipio.findAll();
	}
	
	public List<Municipio> findAllMunicipiosByEstado(Estado estado){
		return repoMunicipio.findDistinctByEstado(estado);
	}
	
	public List<Municipio> findAllMunicipiosByNome(String nome){	
		return repoMunicipio.findDistinctByNomeContainingIgnoreCase(nome);
	}
	
	public List<Municipio> findAllMunicipiosByNomeAndEstado(String nome, Estado estado){	
		return repoMunicipio.findDistinctByNomeContainingIgnoreCaseAndEstado(nome, estado);
	}
}
