package com.ibolsa.api.domain.pg.acionista;

import com.ibolsa.api.domain.pg.abstracts.BaseEntity;
import com.ibolsa.api.domain.pg.municipio.Municipio;
import com.ibolsa.api.enums.EstadoCivilEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Acionista extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conta;
    private String cpfCnpj;

    private String nome;
    private Date dataNascimento;

    private String email;
    private String rgInscricao;

    @Enumerated(EnumType.STRING)
    private EstadoCivilEnum estadoCivil;

    private String telefoneFixo;
    private String telefoneCelular;

    private String cep;
    private String logradouro;
    private Long numero;
    private String bairro;
    private String complemento;
    @ManyToOne
    private Municipio municipio;

    private boolean ativo;

    private String representante;
    private String cpfRepresentante;
    private Long banco;
    private String agencia;
    private String numeroConta;
    private String cpfContaBanco;
    private String nomeContaBanco;
}
