package com.ibolsa.api.domain.pg.contaInvestimento;

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
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class ContaInvestimento extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long conta;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Acionista acionista;

    @Enumerated(EnumType.STRING)
    private TipoContratoEnum tipoContrato;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoContratoEnum integralizacao;

    private BigDecimal qtdLotes;

    private BigDecimal qtdAcoes;

    private BigDecimal valorAcao;

    private BigDecimal aporteTotal;

    private BigDecimal parcelas;

    private BigDecimal aporteMensal;

    private BigDecimal valorAdesao;

    private BigDecimal parcelaAdesao;

    private  BigDecimal valorParcelaAdesao;

    private String observacoes;

    private boolean possuiLinhaCredito;
}
