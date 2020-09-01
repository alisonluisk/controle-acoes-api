package com.ibolsa.api.domain.empresa;

import com.ibolsa.api.domain.municipio.Municipio;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private Date dataAbertura;
    private String logradouro;
    private String numero;
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
    
    public Long getCodigoMunicipio(){
        return getMunicipio().getId();
    }
}
