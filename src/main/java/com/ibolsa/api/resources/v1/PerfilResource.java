package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.usuario.PerfilUsuario;
import com.ibolsa.api.dto.usuario.PerfilDTO;
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
	public ResponseEntity<PerfilDTO> show(@PathVariable Long id) {
		PerfilDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Perfil não encontrado! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<PerfilDTO>> findAll() {
		List<PerfilUsuario> list = service.findAll();
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	private PerfilDTO convertToDto(PerfilUsuario perfilUsuario) {
		return DozerConverter.parseObject(perfilUsuario, PerfilDTO.class);
	}

	private List<PerfilDTO> convertListToDto(List<PerfilUsuario> perfis){
		return DozerConverter.parseListObjects(perfis, PerfilDTO.class);
	}
}
