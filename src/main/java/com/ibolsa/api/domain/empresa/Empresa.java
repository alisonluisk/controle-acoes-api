package com.ibolsa.api.domain.empresa;

import com.ibolsa.api.domain.municipio.Municipio;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable(value = "empresa_log")
public class Empresa implements Serializable {

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
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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
    
    public Long getCodigoMunicipio(){
        return getMunicipio().getId();
    }
    public Long getCodigoMatriz(){
        return getMatriz() != null ? getMatriz().getId() : null;
    }
}
