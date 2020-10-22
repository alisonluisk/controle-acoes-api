package com.ibolsa.api.dto.modeloContrato;

import com.ibolsa.api.enums.FormaPagamentoContratoEnum;
import com.ibolsa.api.enums.TipoContratoEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ModeloContratoDTO implements Serializable {

	private Long id;

	private BigDecimal versao;

	@NotEmpty(message = "Informe o nome do modelo de contrato")
	private String nomeModelo;

	@NotEmpty(message = "Informe o modelo de contrato")
	private String modelo;

	private boolean ativo = true;

	@NotNull(message = "Informe o tipo do modelo")
	private TipoContratoEnum tipoContrato;

	@NotNull(message = "Informe a forma de pagamento do modelo")
	private FormaPagamentoContratoEnum formaPagamento;
}