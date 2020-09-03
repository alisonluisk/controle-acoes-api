package com.ibolsa.api.dto.empresa;

import com.ibolsa.api.dto.localizacao.MunicipioDTO;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmpresaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message = "Informe o cnpj")
	private String cnpj;
	@NotEmpty(message = "Informe a razão social")
	private String razaoSocial;
	private String nomeFantasia;
	private Date dataAbertura;
	@NotEmpty(message = "Informe o logradouro")
	private String logradouro;
	@NotEmpty(message = "Informe o número")
	private String numero;
	private String complemento;
	private String cep;
	@NotEmpty(message = "Informe o bairro")
	private String bairro;
	private MunicipioDTO municipio;
	@NotNull(message = "Informe o código do município")
	private Long codigoMunicipio;
	private String email;
	private String telefone;
	@NotNull(message = "Informe o tipo de empresa")
	private TipoEmpresaEnum tipoEmpresa;
	private Boolean ativo = true;
	private Long qtdAcoes;
	private Long cotasOn;
	private Long cotasPn;

	private Long codigoMatriz;
	private EmpresaDTO matriz;
}