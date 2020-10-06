package com.ibolsa.api.domain.pg.usuario;

import com.ibolsa.api.domain.pg.abstracts.BaseEntity;
import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Usuario extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;

    private String senha;

    private boolean senhaConfigurada = false;

    @ManyToOne
    @JoinColumn(name="acionista_id")
    private Acionista acionista;

    @ManyToOne
    @JoinColumn(name="colaborador_id")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name="perfil_usuario_id")
    private PerfilUsuario perfilUsuario;

    private boolean ativo;

    public Long getCodigoPerfilUsuario(){ return getPerfilUsuario() != null ? getPerfilUsuario().getId() : null; }

}
