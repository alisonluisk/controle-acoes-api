package com.ibolsa.api.repositories.mongo.Impl;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.helper.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
}
