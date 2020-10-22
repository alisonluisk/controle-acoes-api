package com.ibolsa.api.enums;

public enum FormaPagamentoContratoEnum {

    AVISTA(1, "À vista"),
    AINTEGRALIZAR(2, "À integralizar");

    private int cod;
    private String descricao;

    private FormaPagamentoContratoEnum(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }
}
