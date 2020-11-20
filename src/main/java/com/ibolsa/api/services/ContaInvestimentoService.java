package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.contaInvestimento.ContaInvestimento;
import com.ibolsa.api.domain.pg.empresa.Empresa;
import com.ibolsa.api.dto.contaInvestimento.ContaInvestimentoDTO;
import com.ibolsa.api.exceptions.DataIntegrityException;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.repositories.pg.ContaInvestimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaInvestimentoService {
	
	@Autowired
	private ContaInvestimentoRepository repo;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private AcionistaService acionistaService;

	public Optional<ContaInvestimento> find(Long id) {
		return repo.findById(id);
	}

	public List<ContaInvestimento> findByAcionista(Acionista acionista){ return repo.findByAcionista(acionista); }

	public List<ContaInvestimento> findByEmpresa(Empresa empresa){ return repo.findByEmpresa(empresa); }

	public List<ContaInvestimento> findAll() {
		return repo.findAll();
	}

	public ContaInvestimento insert(ContaInvestimento contaInvestimento) {
		contaInvestimento.setConta(repo.getNextConta());
		repo.save(contaInvestimento);

		return contaInvestimento;
	}

	public ContaInvestimento update(ContaInvestimento contaInvestimento) {
		if(contaInvestimento == null || contaInvestimento.getId() == null)
			throw new DataIntegrityException("Acionista id não pode ser nulo.");

		return repo.save(contaInvestimento);
	}

	public ContaInvestimento fromDTO(ContaInvestimentoDTO dto, ContaInvestimento contaInvestimento){
		contaInvestimento.setAcionista(acionistaService.find(dto.getCodigoAcionista()).orElseThrow( () -> new ObjectNotFoundException("Acionista não encontrado! Código: " + dto.getCodigoAcionista())));
		contaInvestimento.setEmpresa(empresaService.find(dto.getCodigoEmpresa()).orElseThrow( () -> new ObjectNotFoundException("Empresa não encontrado! Código: " + dto.getCodigoEmpresa())));

		contaInvestimento.setAporteTotal(dto.getAporteTotal());
		contaInvestimento.setQtdAcoes(dto.getQtdAcoes());
		contaInvestimento.setQtdLotes(dto.getQtdLotes());
		contaInvestimento.setValorAcao(dto.getValorAcao());
		contaInvestimento.setIntegralizacao(dto.getIntegralizacao());
		contaInvestimento.setTipoContrato(dto.getTipoContrato());
		contaInvestimento.setParcelas(dto.getParcelas());
		contaInvestimento.setAporteMensal(dto.getAporteMensal());
		contaInvestimento.setPossuiLinhaCredito(dto.isPossuiLinhaCredito());
		contaInvestimento.setObservacoes(dto.getObservacoes());
		contaInvestimento.setValorAdesao(dto.getValorAdesao());
		contaInvestimento.setParcelaAdesao(dto.getParcelaAdesao());
		contaInvestimento.setValorParcelaAdesao(dto.getValorParcelaAdesao());

		//CRIAR VALIDACOES QUANTO AS REGRAS E CALCULOS

		return contaInvestimento;
	}
}
