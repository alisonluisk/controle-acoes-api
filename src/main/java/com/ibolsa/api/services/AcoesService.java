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
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

		//Gera e salva ações PN
		gerarListasPn(empresa, dto);
		gerarListasOn(empresa, dto);
		log.info(String.format("Ações geradas, Tempo: %s", (new Date().getTime() - dataInicial.getTime())));

		empresa.setStatusAcoes(StatusAcoesEmpresaEnum.CONCLUIDO);
		empresaService.update(empresa);

		log.info(String.format("Processo finalizado para empresa %s de processamento: %s", empresa.getRazaoSocial(), (new Date().getTime() - dataInicial.getTime())));
	}

	private void gerarListasPn(Empresa empresa, AcoesEmpresaDTO dto){
		BigDecimal qtdAcoes = new BigDecimal(empresa.getQtdAcoes()).divide(new BigDecimal(100L), RoundingMode.HALF_UP).multiply(new BigDecimal(dto.getCotasPn()));
		BigDecimal qtdAcaoLote = qtdAcoes.divide(new BigDecimal(dto.getQtdLotes()), RoundingMode.HALF_UP);

		List<Acao> acoes = new ArrayList<>();
		List<String> lotesStr = new ArrayList<>();

		int contAcaoLote = 1;
		int contAcoes = 1;
		for (int i = 1; i <= qtdAcoes.intValue(); i++){
			Acao acao = new Acao();
			acao.setEmpresaId(empresa.getId());
			acao.setTipoAcao(TipoAcaoEnum.PN);
			acao.setAcao(String.format("P%s", StringUtils.leftPad(Integer.toString(i), 7, "0")));

			acao.setValorAcao(dto.getValorAcao());
			acao.setLote(String.format("PH%s", StringUtils.leftPad(Integer.toString(contAcaoLote), 4, "0")));

			if(!lotesStr.contains(acao.getLote()))
				lotesStr.add(acao.getLote());

			acaoRepo.save(acao);

			if(contAcoes >= qtdAcaoLote.intValue()) {
				contAcaoLote++;
				contAcoes = 1;
			}else{
				contAcoes ++;
			}
		}

		gerarListLoteAcoesEmpresa(lotesStr, dto);
	}

	private void gerarListasOn(Empresa empresa, AcoesEmpresaDTO dto){
		BigDecimal qtdAcoesOn = new BigDecimal(empresa.getQtdAcoes()).divide(new BigDecimal(100L), RoundingMode.HALF_UP).multiply(new BigDecimal(dto.getCotasOn()));
		List<Acao> acoes = new ArrayList<>();
		List<LoteAcoesEmpresa> lotes = new ArrayList<>();

		int contAcaoLote = 1;
		int contAcoes = 1;
		for(ParametroEmpresaDTO parametro : dto.getParametroAcoes()){
			BigDecimal qtdAcaoLote = qtdAcoesOn.divide(new BigDecimal(100), RoundingMode.HALF_UP).multiply(new BigDecimal(parametro.getCotasOn()));
			String lote = String.format("PH%s", StringUtils.leftPad(Integer.toString(contAcaoLote), 4, "0"));
			for (int i = 1; i <= qtdAcaoLote.intValue(); i++){
				Acao acao = new Acao();
				acao.setEmpresaId(empresa.getId());
				acao.setTipoAcao(TipoAcaoEnum.ON);
				acao.setAcao(String.format("O%s", StringUtils.leftPad(Integer.toString(contAcoes), 7, "0")));

				acao.setValorAcao(dto.getValorAcao());
				acao.setLote(lote);

				acaoRepo.save(acao);

				contAcoes++;
			}
			LoteAcoesEmpresa loteEmpresa = new LoteAcoesEmpresa();
			loteEmpresa.setEmpresaId(parametro.getEmpresa().getId());
			loteEmpresa.setVendida(false);
			loteEmpresa.setLote(lote);
			loteEmpresa.setTipoLote(TipoAcaoEnum.ON);

			loteRepo.save(loteEmpresa);

			contAcaoLote++;
		}
	}

	private List<LoteAcoesEmpresa> gerarListLoteAcoesEmpresa(List<String> lotes, AcoesEmpresaDTO dto){
		List<LoteAcoesEmpresa> lotesEmpresa = new ArrayList<>();
		int qtdLotesCont = 0;
		for(ParametroEmpresaDTO parametro : dto.getParametroAcoes()){
			BigDecimal qtdLotesPnEmpresa = new BigDecimal(dto.getQtdLotes()).divide(new BigDecimal("100"), RoundingMode.HALF_UP).multiply(new BigDecimal(parametro.getCotasPn())).setScale(0, RoundingMode.HALF_UP);

			int cont = 0;
			while(cont < qtdLotesPnEmpresa.intValue()){
				LoteAcoesEmpresa loteEmpresa = new LoteAcoesEmpresa();
				loteEmpresa.setEmpresaId(parametro.getEmpresa().getId());
				loteEmpresa.setVendida(false);
				loteEmpresa.setLote(lotes.get(qtdLotesCont));
				loteEmpresa.setTipoLote(TipoAcaoEnum.PN);

				loteRepo.save(loteEmpresa);

				cont ++;
				qtdLotesCont ++;
			}
		}
		return lotesEmpresa;
	}

}
