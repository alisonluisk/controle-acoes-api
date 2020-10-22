package com.ibolsa.api.enums;

public enum TipoContratoEnum {

    FLEX(1, "Flex"),
    PRIME(2, "Prime"),
    LIS_MONEY(3, "Lis Money");

    private int cod;
    private String descricao;

    private TipoContratoEnum(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }
}
