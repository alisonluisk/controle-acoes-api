package com.ibolsa.api.domain.empresa;

import com.ibolsa.api.domain.municipio.Municipio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@AuditTable(value = "parametro_empresa_log")
public class ParametroEmpresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    private Long cotasOn;
    private Long cotasPn;

}
