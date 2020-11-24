package com.ibolsa.api.services;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.repositories.mongo.DadosVendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DadosVendaService {
	
	@Autowired
	private DadosVendaRepository repository;

	public List<DadosVenda> findByParams(Long codigoAcionista, Date competencia){
		return repository.findAllParams(codigoAcionista, competencia);
	}
}
