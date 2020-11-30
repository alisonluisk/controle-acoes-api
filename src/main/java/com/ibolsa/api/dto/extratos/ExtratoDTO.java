package com.ibolsa.api.dto.extratos;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.dto.acionista.AcionistaDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ExtratoDTO {

    private AcionistaDTO acionista;

    private List<DadosVenda> movimentacoes;

    private BigDecimal limiteCredito;

    private BigDecimal creditoDisponivel;

    private BigDecimal valorIntegralizado;

    private BigDecimal valorAIntegralizar;

    private BigDecimal valorConsumoCompetencia;

    private BigDecimal valorEconomiaCompetencia;

    private BigDecimal valorMercadoCompetencia;

    private BigDecimal valorAplicacao;

    private Date data;

    private Long qtdMesesTotal;

    private Long qtdMesDecorrido;

}
