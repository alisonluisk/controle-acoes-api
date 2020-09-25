package com.ibolsa.api.domain.pg.colaborador;

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
public class Colaborador extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String nome;
    private String email;
    private String rg;
    private Date dataNascimento;
    private String telefoneFixo;
    private String telefoneCelular;

    @Enumerated(EnumType.STRING)
    private EstadoCivilEnum estadoCivil;
    private String cep;
    private String logradouro;
    private Long numero;
    private String bairro;

    @ManyToOne
    private Municipio municipio;
    private String complemento;
    private boolean ativo;

    public Long getCodigoMunicipio(){
        return getMunicipio().getId();
    }
}
