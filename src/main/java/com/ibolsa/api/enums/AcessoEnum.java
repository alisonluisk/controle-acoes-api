package com.ibolsa.api.enums;

public enum AcessoEnum {

    ADMIN(1, "ROLE_ADMIN"),
    USUARIO(2, "ROLE_USUARIO"),
    CLIENTE(3, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    private AcessoEnum(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }
}
