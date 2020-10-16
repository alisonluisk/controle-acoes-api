package com.ibolsa.api.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleEnum implements GrantedAuthority {

    ESTADO_READ(1, "ROLE_ESTADO_READ"),
    ROLE_USUARIO(2, "ROLE_USUARIO"),
    ROLE_CLIENTE(3, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    private RoleEnum(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    @Override
    public String getAuthority() {
        return getDescricao();
    }
}
