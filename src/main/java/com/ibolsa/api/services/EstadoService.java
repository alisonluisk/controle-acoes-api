package com.ibolsa.api.services;

import com.ibolsa.api.domain.municipio.Estado;
import com.ibolsa.api.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public Optional<Estado> find(Long id) {
		return repo.findById(id);
	}
	
	public Optional<Estado> findBySigla(String sigla) {
		return repo.findBySigla(sigla);
	}
	
	public List<Estado> findAll() {
		return repo.findAll();
	}
}
