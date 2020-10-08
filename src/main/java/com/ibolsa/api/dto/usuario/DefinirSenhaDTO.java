package com.ibolsa.api.dto.usuario;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
@Builder
public class DefinirSenhaDTO {

	@NotEmpty(message = "Informe a senha")
	private String senha;

	public DefinirSenhaDTO(String senha) {
		super();
		this.senha = senha;
	}
}
