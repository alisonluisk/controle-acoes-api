package com.ibolsa.api.dto.localizacao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CepDTO implements Serializable {

    private String logradouro;
    private String bairro;
    private String cep;
    private String complemento;
    private String ibge;
    private Boolean erro;

    private MunicipioDTO municipio;

}
