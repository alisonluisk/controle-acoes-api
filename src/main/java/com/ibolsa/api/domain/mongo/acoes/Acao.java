package com.ibolsa.api.domain.mongo.acoes;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "acoes")
@Data
public class Acao {

    @Id
    private String _id;

    private String acao;
    @Field(name = "empresa_id")
    private Long empresaId;
    private String lote;

    private String tipoAcao;
    private BigDecimal valorAcao;

}
