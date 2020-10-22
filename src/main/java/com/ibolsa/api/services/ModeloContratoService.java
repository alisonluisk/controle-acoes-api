package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.modeloContrato.ModeloContrato;
import com.ibolsa.api.repositories.pg.ModeloContratoRepository;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ModeloContratoService {
	
	@Autowired
	private ModeloContratoRepository repo;

	public Optional<ModeloContrato> find(Long id) {
		return repo.findById(id);
	}

	public List<ModeloContrato> findAll(Boolean ativo) {
		if(ativo == null)
			return repo.findAll();
		return repo.findByAtivo(ativo);
	}


	public void gerarContrato(){
		Map<String, String> substitutes = new HashMap<>();
		substitutes.put("name", "John");
		substitutes.put("college", "University of Stanford");
		String templateString = "My name is <b>${name}</b> and I am a student at the ${college}.";
		StringSubstitutor sub = new StringSubstitutor(substitutes);
		String result = sub.replace(templateString);
	}

}
