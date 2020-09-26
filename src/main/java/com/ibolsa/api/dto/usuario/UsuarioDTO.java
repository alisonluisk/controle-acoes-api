package com.ibolsa.api.dto.usuario;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UsuarioDTO implements Serializable {

    private Long id;
    private String usuario;
    @NotNull(message = "Informe o código do perfil")
    private Long codigoPerfilUsuario;
    private PerfilUsuarioDTO perfilUsuario;
    private Acionista acionista;
    private Colaborador colaborador;

//    private String nomePerfil;

//    private Set<Integer> perfilAcesso;
}
