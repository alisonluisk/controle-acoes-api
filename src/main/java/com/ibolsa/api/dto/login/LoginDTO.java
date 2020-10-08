package com.ibolsa.api.dto.login;

import com.ibolsa.api.config.UserSS;
import com.ibolsa.api.domain.pg.usuario.Usuario;
import com.ibolsa.api.dto.acionista.AcionistaDTO;
import com.ibolsa.api.dto.colaborador.ColaboradorDTO;
import com.ibolsa.api.helper.DozerConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class LoginDTO implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String usuario;

	private String nome;
	
	public LoginDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.usuario = usuario.getUsuario();

		if(usuario.getAcionista() != null) {
			this.nome = usuario.getAcionista().getNome();
		}
		if(usuario.getColaborador() != null) {
			this.nome = usuario.getColaborador().getNome();
		}
	}
	
	public LoginDTO(UserSS usuario) {
		this.id = usuario.getId();
	}
}
