package com.ibolsa.api.domain.mongo.dadosVenda;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "dados_vendas")
@Data
public class DadosVenda {

    @Id
    private String _id;

    @Field(name = "codigoCliente")
    private Integer codigoCliente;

    private String nomeCliente;

    private String cpfCnpjCliente;

    private String enderecoCliente;

    private Integer codigoEmpresa;

    private String cnpjEmpresa;

    private Integer codigoProduto;

    private String nomeProduto;

    private BigDecimal precoVenda;

    private BigDecimal valorUnitario;

    private BigDecimal quantidade;

    private BigDecimal totalVenda;

    private BigDecimal totalMercado;

    private BigDecimal totalDesconto;

    private Integer codigoVenda;

    private Integer codigoItemVenda;

    private Date dataVenda;

    private Integer codigoAcionista;

    private Integer codigoConvenio;

    private Integer codigoGrupo;

    private String nomeGrupo;

}
