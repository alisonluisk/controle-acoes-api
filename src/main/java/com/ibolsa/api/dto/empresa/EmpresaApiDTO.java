package com.ibolsa.api.dto.empresa;

import com.ibolsa.api.dto.localizacao.CepDTO;
import lombok.Data;

@Data
public class EmpresaApiDTO {

    private String nome;
    private String telefone;
    private String bairro;
    private String numero;
    private String cep;
    private String abertura;
    private String cnpj;
    private String fantasia;
    private String complemento;
    private String email;
    private String logradouro;
    private String municipio;
    private String uf;
    private CepDTO cepObj;
    private String status;
}
