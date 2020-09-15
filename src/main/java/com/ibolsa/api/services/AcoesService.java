package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.empresa.Empresa;
import com.ibolsa.api.dto.empresa.AcoesEmpresaDTO;
import com.ibolsa.api.repositories.mongo.AcaoRepository;
import com.ibolsa.api.repositories.mongo.LoteAcoesEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcoesService {
	
	@Autowired
	private AcaoRepository acaoRepo;

	@Autowired
	private LoteAcoesEmpresaRepository loteRepo;

	public void gerarAcoes(Empresa empresa, AcoesEmpresaDTO dto){

	}
}
