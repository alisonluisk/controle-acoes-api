package com.ibolsa.api.dto.dashboard.totalizadorMes;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalizadorMes {

    private Long mes;

    private Long ano;

    private String dataFormatada;

    private BigDecimal totalVenda;

    private BigDecimal totalDesconto;

    private BigDecimal totalMercado;

    private BigDecimal quantidade;

    private BigDecimal totalClienteAcionista;

    private BigDecimal totalClienteOutros;

    private Long codigoConvenio;

}
