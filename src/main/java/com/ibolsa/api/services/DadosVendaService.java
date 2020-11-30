package com.ibolsa.api.services;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.contaInvestimento.ContaInvestimento;
import com.ibolsa.api.dto.acionista.AcionistaDTO;
import com.ibolsa.api.dto.dashboard.totalizadorMes.TotalizadorMes;
import com.ibolsa.api.dto.extratos.ExtratoDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.repositories.mongo.DadosVendaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class DadosVendaService {
	
	@Autowired
	private DadosVendaRepository repository;

	@Autowired
	private AcionistaService acionistaService;

	@Autowired
	private ContaInvestimentoService contaInvestimentoService;

	private static final Long CODIGOCONVENIO = 51L;

	public List<DadosVenda> getAllParams(Long codigoAcionista, Date competencia){
		Acionista acionista = acionistaService.find(codigoAcionista).orElseThrow( () -> new ObjectNotFoundException("Acionista não encontrado! Código: " + codigoAcionista));
		verificarDadosVendaSemCodigoAcionista(acionista);
		return repository.findAllParams(codigoAcionista, competencia);
	}

	public ExtratoDTO getExtratoMensal(Long codigoAcionista, Date competencia){
		//Verificar vendas do acionista, sem ligacao.
		Acionista acionista = acionistaService.find(codigoAcionista).orElseThrow( () -> new ObjectNotFoundException("Acionista não encontrado! Código: " + codigoAcionista));
		verificarDadosVendaSemCodigoAcionista(acionista);

		List<DadosVenda> movimentacoes = repository.findAllParams(codigoAcionista, competencia);

		ExtratoDTO dto = new ExtratoDTO();
		dto.setAcionista(DozerConverter.parseObject(acionista, AcionistaDTO.class));
		dto.setMovimentacoes(movimentacoes);
		dto.setData(competencia);
		dto.setValorConsumoCompetencia(movimentacoes.stream().map(DadosVenda::getTotalVenda).reduce(BigDecimal.ZERO, BigDecimal::add));
		dto.setValorEconomiaCompetencia(movimentacoes.stream().map(DadosVenda::getTotalDesconto).reduce(BigDecimal.ZERO, BigDecimal::add));
		dto.setValorMercadoCompetencia(movimentacoes.stream().map(DadosVenda::getTotalMercado).reduce(BigDecimal.ZERO, BigDecimal::add));

		List<ContaInvestimento> contas = contaInvestimentoService.findByAcionista(acionista);
		BigDecimal qtdMesesTotal = BigDecimal.ONE;
		for(ContaInvestimento conta : contas){
			if(conta.getParcelas().compareTo(qtdMesesTotal) > 0)
				qtdMesesTotal = conta.getParcelas();


		}

//		private BigDecimal limiteCredito;
//		private BigDecimal valorIntegralizado;
//		private BigDecimal valorAIntegralizar;
//		private BigDecimal valorAplicacao;
//		private Long qtdMesesTotal;
//		private Long qtdMesDecorrido;

		//Buscar valor de movimentacoes até data limite competencia/
//		private BigDecimal creditoDisponivel;

		return null;
	}

	public List<TotalizadorMes> getTotalDozeMeses(){
		List<TotalizadorMes> list = repository.getTotalUltimosDozeMeses();
		List<TotalizadorMes> lista = new ArrayList<>(list);
		Collections.reverse(lista);
		return lista;
	}

	public void verificarDadosVendaSemCodigoAcionista(Acionista acionista){
		List<DadosVenda> lista = repository.findByCpfCnpjClienteAndCodigoConvenioAndCodigoAcionistaIsNull(acionista.getCpfCnpj(), CODIGOCONVENIO);
		lista.stream().forEach(venda -> {
			venda.setCodigoAcionista(acionista.getId().intValue());
			update(venda);
		});
	}

	public void update(DadosVenda venda){
		repository.save(venda);
	}
}
