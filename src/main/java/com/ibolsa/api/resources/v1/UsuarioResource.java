package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import com.ibolsa.api.domain.pg.usuario.AlterarSenhaDTO;
import com.ibolsa.api.domain.pg.usuario.DefinirSenhaDTO;
import com.ibolsa.api.domain.pg.usuario.Usuario;
import com.ibolsa.api.dto.colaborador.ColaboradorDTO;
import com.ibolsa.api.dto.usuario.UsuarioDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.ColaboradorService;
import com.ibolsa.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<UsuarioDTO> show(@PathVariable Long id) {
		UsuarioDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Usuário não encontrado! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(@RequestParam(value="ativo", required=false) Boolean ativo) {
		List<Usuario> list = service.findAll(ativo);
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<UsuarioDTO> update(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
		UsuarioDTO response = service.find(id).map( usuario -> {
			service.fromDTO(usuarioDTO, usuario);
			return convertToDto(service.update(usuario));
		}).orElseThrow( () -> new ObjectNotFoundException("Usuário não encontrado! Código: " + id));

		return ResponseEntity.ok().body(response);
	}

	@PutMapping(value="/{id}/alterar_senha")
	public ResponseEntity<UsuarioDTO> alterarSenha(@Valid @RequestBody AlterarSenhaDTO objDto, @PathVariable Long id) {
		UsuarioDTO response = service.find(id).map( usuario -> convertToDto(service.alterarSenha(objDto, usuario))).orElseThrow( () -> new ObjectNotFoundException("Usuário não encontrado! Código: " + id));

		return ResponseEntity.ok().body(response);
	}

	@PutMapping(value="/{id}/definirSenha")
	public ResponseEntity<UsuarioDTO> definirSenha(@Valid @RequestBody DefinirSenhaDTO objDto, @PathVariable Long id) {
		UsuarioDTO response = service.find(id).map( usuario -> convertToDto(service.definirSenha(objDto, usuario))).orElseThrow( () -> new ObjectNotFoundException("Usuário não encontrado! Código: " + id));

		return ResponseEntity.ok().body(response);
	}

	private UsuarioDTO convertToDto(Usuario usuario) {
		usuario.setNomeUsuario();
		return DozerConverter.parseObject(usuario, UsuarioDTO.class);
	}

	private List<UsuarioDTO> convertListToDto(List<Usuario> usuarios){
		return DozerConverter.parseListObjects(usuarios, UsuarioDTO.class);
	}
}
