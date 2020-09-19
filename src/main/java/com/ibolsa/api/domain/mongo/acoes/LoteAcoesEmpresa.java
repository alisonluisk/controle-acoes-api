package com.ibolsa.api.domain.mongo.acoes;

import com.ibolsa.api.enums.TipoAcaoEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "lote_acoes_empresa")
@Data
public class LoteAcoesEmpresa {

    @Id
    private String _id;

    @Field(name = "empresa_id")
    private Long empresaId;

    private String lote;
    private Boolean vendida;
    private TipoAcaoEnum tipoLote;
}
