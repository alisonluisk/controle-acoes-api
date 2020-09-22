package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.usuario.Perfil;
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
		List<Perfil> list = service.findAll();
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	private PerfilDTO convertToDto(Perfil perfil) {
		return DozerConverter.parseObject(perfil, PerfilDTO.class);
	}

	private List<PerfilDTO> convertListToDto(List<Perfil> perfis){
		return DozerConverter.parseListObjects(perfis, PerfilDTO.class);
	}
}
