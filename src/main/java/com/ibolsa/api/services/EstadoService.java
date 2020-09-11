package com.ibolsa.api.services;

import com.ibolsa.api.domain.mongo.acoes.Acao;
import com.ibolsa.api.domain.pg.municipio.Estado;
import com.ibolsa.api.domain.pg.municipio.Municipio;
import com.ibolsa.api.repositories.mongo.AcaoRepository;
import com.ibolsa.api.repositories.pg.EstadoRepository;
import com.ibolsa.api.repositories.pg.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;

	@Autowired
	private MunicipioRepository munRepository;

	@Autowired
	private AcaoRepository acaoRepository;
	
	public Optional<Estado> find(Long id) {
		return repo.findById(id);
	}
	
	public Optional<Estado> findBySigla(String sigla) {
		return repo.findBySigla(sigla);
	}
	
	public List<Estado> findAll() {
		Acao acao = new Acao();
		acao.setId("PHA002");
		acao.setEmpresaId(1L);
		acao.setLote("LTPHA001");
		acao.setTipoAcao("ON");
		acao.setValorAcao(new BigDecimal("2"));
		acaoRepository.save(acao);

		Municipio mun = new Municipio();
		mun.setId(9999999L);
		mun.setNome("TESTE");
		mun.setEstado(repo.findBySigla("PR").get());
		munRepository.save(mun);

		return repo.findAll();
	}
}
