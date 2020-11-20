package com.ibolsa.api.dto.contaInvestimento;

import com.ibolsa.api.domain.pg.abstracts.BaseEntity;
import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.empresa.Empresa;
import com.ibolsa.api.enums.FormaPagamentoContratoEnum;
import com.ibolsa.api.enums.TipoContratoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ContaInvestimentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long conta;

    private Empresa empresa;

    @NotNull(message = "Informe o código da empresa")
    private Long codigoEmpresa;

    private Acionista acionista;

    @NotNull(message = "Informe o código do acionista")
    private Long codigoAcionista;

    @NotNull(message = "Informe o tipo de contrato")
    private TipoContratoEnum tipoContrato;

    @NotNull(message = "Informe o tipo de integralização")
    private FormaPagamentoContratoEnum integralizacao;

    @NotNull(message = "Informe a quantidade de lotes")
    private BigDecimal qtdLotes;

    @NotNull(message = "Informe a quantidade de ações")
    private BigDecimal qtdAcoes;

    @NotNull(message = "Informe o valor das ações")
    private BigDecimal valorAcao;

    @NotNull(message = "Informe o valor do aporte total")
    private BigDecimal aporteTotal;

    private BigDecimal parcelas;

    private BigDecimal aporteMensal;

    private BigDecimal valorAdesao;

    private BigDecimal parcelaAdesao;

    private  BigDecimal valorParcelaAdesao;

    private String observacoes;

    private boolean possuiLinhaCredito;
}
