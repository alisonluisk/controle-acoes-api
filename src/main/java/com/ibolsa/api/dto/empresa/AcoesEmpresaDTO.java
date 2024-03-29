package com.ibolsa.api.dto.empresa;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class AcoesEmpresaDTO implements Serializable {

    @NotNull(message = "Cotas ON deve ser informado")
    private Long cotasOn;
    @NotNull(message = "Cotas PN deve ser informado")
    private Long cotasPn;
    @NotNull(message = "Quantidade de lotes deve ser informado")
    private Long qtdLotes;
    @NotNull(message = "Valor da ação deve ser informado")
    private BigDecimal valorAcao;
    @NotEmpty(message = "Deve ser informado os paramêtros para divisão das ações")
    private List<ParametroEmpresaDTO> parametroAcoes;

}
