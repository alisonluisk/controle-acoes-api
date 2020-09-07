package com.ibolsa.api.dto.empresa;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ParametroEmpresaDTO implements Serializable {

    private Long id;
    @NotNull(message = "Cotas ON deve ser informado")
    private Long cotasOn;
    @NotNull(message = "Cotas PN deve ser informado")
    private Long cotasPn;

    private EmpresaDTO empresa;
}
