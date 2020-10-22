package com.ibolsa.api.domain.pg.modeloContrato;

import com.ibolsa.api.domain.pg.abstracts.BaseEntity;
import com.ibolsa.api.enums.FormaPagamentoContratoEnum;
import com.ibolsa.api.enums.TipoContratoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class ModeloContrato extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal versao;

    private String nomeModelo;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String modelo;

    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private TipoContratoEnum tipoContrato;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoContratoEnum formaPagamento;
}
