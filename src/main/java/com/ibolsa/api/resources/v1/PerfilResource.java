package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.usuario.PerfilUsuario;
import com.ibolsa.api.dto.usuario.PerfilUsuarioDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/perfis")
public class PerfilResource {
	
	@Autowired
	private PerfilService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<PerfilUsuarioDTO> show(@PathVariable Long id) {
		PerfilUsuarioDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Perfil não encontrado! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<PerfilUsuarioDTO>> findAll() {
		List<PerfilUsuario> list = service.findAll();
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	private PerfilUsuarioDTO convertToDto(PerfilUsuario perfilUsuario) {
		return DozerConverter.parseObject(perfilUsuario, PerfilUsuarioDTO.class);
	}

	private List<PerfilUsuarioDTO> convertListToDto(List<PerfilUsuario> perfis){
		return DozerConverter.parseListObjects(perfis, PerfilUsuarioDTO.class);
	}
}
