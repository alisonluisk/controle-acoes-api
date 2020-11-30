package com.ibolsa.api.repositories.mongo.Impl;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.dto.dashboard.totalizadorMes.TotalizadorMes;
import com.ibolsa.api.helper.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

@Slf4j
public class DadosVendaRepositoryImpl implements DadosVendaRepositoryCustom {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public DadosVendaRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<DadosVenda> findAllParams(Long codigoAcionista, Date competencia) {
		Query query = new Query(
				Criteria.where("codigoAcionista").is(codigoAcionista)
						.andOperator(Criteria.where("dataVenda").gte(DateHelper.zerarHoras(DateHelper.getPrimeiroDiaMes(competencia))).lt(DateHelper.ultimaHoras(DateHelper.getUltimoDiaMes(competencia))))
		);

		return mongoTemplate.find(query, DadosVenda.class);
	}

	@Override
	public List<TotalizadorMes> getTotalUltimosDozeMeses() {
		String addFields = "{ $addFields: { mes: { $month: '$dataVenda' }, ano: { $year: '$dataVenda' }, dataFormatada: { $dateToString: { format: '%m/%Y', date: '$dataVenda' }}, totalVenda: { $toDecimal: '$totalVenda' }, totalDesconto: { $toDecimal: '$totalDesconto'}, totalMercado: { $toDecimal: '$totalMercado' }, quantidade: { $toDecimal: '$quantidade' }}} ";
		TypedAggregation<TotalizadorMes> aggregation = Aggregation.newAggregation(
				TotalizadorMes.class,
				new CustomAggregationOperation(addFields),
				getGroupOperation("ano", "mes"),
				Aggregation.sort(Sort.Direction.DESC, "_id.ano", "_id.mes"),
				Aggregation.limit(12)
		);

		AggregationResults<TotalizadorMes> results = mongoTemplate.aggregate(aggregation, "dados_vendas", TotalizadorMes.class);

		List<TotalizadorMes> totalConvenio = getTotalUltimosDozeMesesConvenio();
		List<TotalizadorMes> resultado =results.getMappedResults();

		for(TotalizadorMes tot : resultado){
			for(TotalizadorMes todConvenio : totalConvenio){
				if(tot.getAno().equals(todConvenio.getAno()) && tot.getMes().equals(todConvenio.getMes())) {
					tot.setTotalClienteAcionista(todConvenio.getTotalMercado());
					tot.setTotalClienteOutros(tot.getTotalMercado().subtract(tot.getTotalClienteAcionista()));
				}
			}
		}

		return resultado;
	}

	private List<TotalizadorMes> getTotalUltimosDozeMesesConvenio() {
		String addFields = "{ $addFields: { mes: { $month: '$dataVenda' }, ano: { $year: '$dataVenda' }, dataFormatada: { $dateToString: { format: '%m/%Y', date: '$dataVenda' }}, totalVenda: { $toDecimal: '$totalVenda' }, totalDesconto: { $toDecimal: '$totalDesconto'}, totalMercado: { $toDecimal: '$totalMercado' }, quantidade: { $toDecimal: '$quantidade' }}} ";
		TypedAggregation<TotalizadorMes> aggregation = Aggregation.newAggregation(
				TotalizadorMes.class,
				Aggregation.match(new Criteria("codigoConvenio").is(51)),
				new CustomAggregationOperation(addFields),
				getGroupOperation("ano", "mes", "codigoConvenio"),
				Aggregation.sort(Sort.Direction.DESC, "_id.ano", "_id.mes"),
				Aggregation.limit(12)
		);

		AggregationResults<TotalizadorMes> results = mongoTemplate.aggregate(aggregation, "dados_vendas", TotalizadorMes.class);
		return results.getMappedResults();
	}

	private GroupOperation getGroupOperation(String...fields) {
		return Aggregation.group(fields)
				.sum("totalVenda").as("totalVenda")
				.sum("totalDesconto").as("totalDesconto")
				.sum("totalMercado").as("totalMercado")
				.sum("quantidade").as("quantidade")
				.last("mes").as("mes")
				.last("ano").as("ano")
				.last("dataFormatada").as("dataFormatada");
	}
}
