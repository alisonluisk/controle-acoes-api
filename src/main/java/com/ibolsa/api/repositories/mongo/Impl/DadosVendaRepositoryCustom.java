package com.ibolsa.api.repositories.mongo.Impl;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.dto.dashboard.totalizadorMes.TotalizadorMes;

import java.util.Date;
import java.util.List;

public interface DadosVendaRepositoryCustom {

	List<DadosVenda> findAllParams(Long codigoAcionista, Date competencia);

	List<TotalizadorMes> getTotalUltimosDozeMeses();

	
}
