package com.ibolsa.api.dto.usuario;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import com.ibolsa.api.domain.pg.usuario.Perfil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UsuarioDTO implements Serializable {

    private Long id;
    private String usuario;
    @NotNull(message = "Informe o código do perfil")
    private Long codigoPerfil;
    private Perfil perfil;
    private Acionista acionista;
    private Colaborador colaborador;

//    private Set<Integer> perfilAcesso;
}
