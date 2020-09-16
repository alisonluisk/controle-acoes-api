package com.ibolsa.api.domain.pg.empresa;

import com.ibolsa.api.domain.pg.abstracts.BaseEntity;
import com.ibolsa.api.domain.pg.municipio.Municipio;
import com.ibolsa.api.enums.StatusAcoesEmpresaEnum;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Empresa extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private Date dataAbertura;
    private String logradouro;
    private Long numero;
    private String complemento;
    private String cep;
    private String bairro;
    @ManyToOne
    private Municipio municipio;
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private TipoEmpresaEnum tipoEmpresa;

    private boolean ativo = true;

    private Long qtdAcoes;

    @OneToOne
    @JoinColumn(name="matriz_id")
    private Empresa matriz;

    @Enumerated(EnumType.STRING)
    private StatusAcoesEmpresaEnum statusAcoes;
    
    public Long getCodigoMunicipio(){
        return getMunicipio().getId();
    }
    public Long getCodigoMatriz(){
        return getMatriz() != null ? getMatriz().getId() : null;
    }
}
