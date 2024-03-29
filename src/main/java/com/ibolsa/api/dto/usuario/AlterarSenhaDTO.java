package com.ibolsa.api.dto.usuario;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
@Builder
public class AlterarSenhaDTO {

	@NotEmpty(message = "Informe a senha atual")
	private String senhaAtual;

	@NotEmpty(message = "Informe a senha")
	private String senha;

	public AlterarSenhaDTO(String senhaAtual, String novaSenha) {
		super();
		this.senhaAtual = senhaAtual;
		this.senha = novaSenha;
	}
}
