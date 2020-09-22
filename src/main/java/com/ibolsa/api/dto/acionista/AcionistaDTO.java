package com.ibolsa.api.dto.acionista;

import com.ibolsa.api.domain.pg.municipio.Municipio;
import com.ibolsa.api.dto.localizacao.MunicipioDTO;
import com.ibolsa.api.enums.EstadoCivilEnum;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class AcionistaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String conta;
	@NotEmpty(message = "Informe o cpf/cnpj")
	private String cpfCnpj;
	@NotEmpty(message = "Informe o nome")
	private String nome;
	private String rgInscricao;
	private Date dataNascimento;

	@NotEmpty(message = "Informe o logradouro")
	private String logradouro;
	@NotNull(message = "Informe o número")
	private Long numero;
	private String complemento;
	private String cep;
	@NotEmpty(message = "Informe o bairro")
	private String bairro;
	private MunicipioDTO municipio;
	@NotNull(message = "Informe o código do município")
	private Long codigoMunicipio;
	private String email;
	private String telefoneFixo;
	private String telefoneCelular;
	@NotNull(message = "Informe o estado civil")
	private EstadoCivilEnum estadoCivil;
	private Boolean ativo = true;

	private String representante;
	private String cpfRepresentante;
	private Long banco;
	private String agencia;
	private String numeroConta;
	private String cpfContaBanco;
	private String nomeContaBanco;
}