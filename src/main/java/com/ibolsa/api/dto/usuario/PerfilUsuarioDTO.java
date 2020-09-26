package com.ibolsa.api.dto.usuario;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class PerfilUsuarioDTO implements Serializable {

    private Long id;
    @NotEmpty(message = "Informe o nome")
    private String nome;
    @NotEmpty(message = "Informe a descrição")
    private String descricao;

    private Set<Integer> perfilAcesso;
}
