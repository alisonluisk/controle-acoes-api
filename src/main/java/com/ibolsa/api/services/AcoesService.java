package com.ibolsa.api.services;

import com.ibolsa.api.domain.mongo.acoes.Acao;
import com.ibolsa.api.domain.pg.empresa.Empresa;
import com.ibolsa.api.dto.empresa.AcoesEmpresaDTO;
import com.ibolsa.api.enums.StatusAcoesEmpresaEnum;
import com.ibolsa.api.enums.TipoAcaoEnum;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import com.ibolsa.api.exceptions.InformacoesInvalidasException;
import com.ibolsa.api.repositories.mongo.AcaoRepository;
import com.ibolsa.api.repositories.mongo.LoteAcoesEmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
		//Validar tipo empresa.
		if(empresa.getTipoEmpresa().equals(TipoEmpresaEnum.HOLDING))
			throw new InformacoesInvalidasException("Não é possivel gerar ações para empresas HOLDINGS");

		Date dataInicial = new Date();
		empresa.setStatusAcoes(StatusAcoesEmpresaEnum.EM_ANDAMENTO);
		empresaService.update(empresa);

		List<Acao> acoes = getListAcoes(TipoAcaoEnum.PN, empresa, dto.getValorAcao(), dto.getCotasPn(), dto.getQtdLotes());
		acaoRepo.saveAll(acoes);

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
}
