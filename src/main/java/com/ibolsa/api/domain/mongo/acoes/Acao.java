package com.ibolsa.api.domain.mongo.acoes;

import com.ibolsa.api.enums.TipoAcaoEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private TipoAcaoEnum tipoAcao;
    private BigDecimal valorAcao;

}
