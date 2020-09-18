package com.ibolsa.api.services;

import com.ibolsa.api.domain.mongo.acoes.Acao;
import com.ibolsa.api.domain.mongo.acoes.LoteAcoesEmpresa;
import com.ibolsa.api.domain.pg.empresa.Empresa;
import com.ibolsa.api.dto.empresa.AcoesEmpresaDTO;
import com.ibolsa.api.dto.empresa.ParametroEmpresaDTO;
import com.ibolsa.api.enums.StatusAcoesEmpresaEnum;
import com.ibolsa.api.enums.TipoAcaoEnum;
import com.ibolsa.api.repositories.mongo.AcaoRepository;
import com.ibolsa.api.repositories.mongo.LoteAcoesEmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AcoesService {
	
	@Autowired
	private AcaoRepository acaoRepo;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private LoteAcoesEmpresaRepository loteRepo;

	@Async("processExecutor")
	public void gerarAcoes(Empresa empresa, AcoesEmpresaDTO dto){
		log.info(String.format("Iniciando geração de ações da empresa %s", empresa.getRazaoSocial()));

		Date dataInicial = new Date();

		List<Acao> acoes = getListAcoes(TipoAcaoEnum.PN, empresa, dto.getValorAcao(), dto.getCotasPn(), dto.getQtdLotes());
		List<String> lotes = new ArrayList<>();
		acoes.forEach(acao-> { if(!lotes.contains(acao.getLote())) lotes.add(acao.getLote()); });
		acaoRepo.saveAll(acoes);

		log.info("Ações geradas");

		List<LoteAcoesEmpresa> lotesEmpresa = gerarListLoteAcoesEmpresa(lotes, dto);
		loteRepo.saveAll(lotesEmpresa);

		log.info("Lotes gerados");

		empresa.setStatusAcoes(StatusAcoesEmpresaEnum.CONCLUIDO);
		empresaService.update(empresa);

		log.info(String.format("Finalizado processo de geração de ações da empresa %s", empresa.getRazaoSocial()));
		log.info(String.format("Tempo de processamento: %s", (new Date().getTime() - dataInicial.getTime())));
	}

	private List<Acao> getListAcoes(TipoAcaoEnum tipo, Empresa empresa, BigDecimal valorAcao, Long percentualCotas, Long qtdLotes){
		BigDecimal qtdAcoes = new BigDecimal(empresa.getQtdAcoes()).divide(new BigDecimal(100L)).multiply(new BigDecimal(percentualCotas));
		BigDecimal qtdAcaoLote = qtdAcoes.divide(new BigDecimal(qtdLotes));

		List<Acao> acoes = new ArrayList<>();
		Integer contAcaoLote = 1;
		Integer contAcoes = 1;
		for (Integer i = 1; i <= qtdAcoes.intValue(); i++){
			Acao acao = new Acao();
			acao.setEmpresaId(empresa.getId());
			acao.setTipoAcao(tipo);
			if(tipo.equals(TipoAcaoEnum.PN))
				acao.setAcao(String.format("P%s", StringUtils.leftPad(i.toString(), 7, "0")));
			else acao.setAcao(String.format("O%s", StringUtils.leftPad(i.toString(), 7, "0")));

			acao.setValorAcao(valorAcao);
			acao.setLote(String.format("PH%s", StringUtils.leftPad(contAcaoLote.toString(), 4, "0")));

			acoes.add(acao);

			if(contAcoes >= qtdAcaoLote.intValue()) {
				contAcaoLote++;
				contAcoes = 1;
			}else{
				contAcoes ++;
			}
		}
		return acoes;
	}

	private List<LoteAcoesEmpresa> gerarListLoteAcoesEmpresa(List<String> lotes, AcoesEmpresaDTO dto){
		List<LoteAcoesEmpresa> lotesEmpresa = new ArrayList<>();
		Integer qtdLotesCont = 0;
		for(ParametroEmpresaDTO parametro : dto.getParametroAcoes()){
			BigDecimal qtdLotesPnEmpresa = new BigDecimal(dto.getQtdLotes()).divide(new BigDecimal("100")).multiply(new BigDecimal(parametro.getCotasPn())).setScale(0, RoundingMode.HALF_UP);

			Integer cont = 0;
			while(cont < qtdLotesPnEmpresa.intValue()){
				LoteAcoesEmpresa loteEmpresa = new LoteAcoesEmpresa();
				loteEmpresa.setEmpresaId(parametro.getEmpresa().getId());
				loteEmpresa.setVendida(false);
				loteEmpresa.setLote(lotes.get(qtdLotesCont));
				lotesEmpresa.add(loteEmpresa);
				cont ++;
				qtdLotesCont ++;
			}
		}
		return lotesEmpresa;
	}

}
