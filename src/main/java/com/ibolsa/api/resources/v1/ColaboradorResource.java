package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import com.ibolsa.api.dto.colaborador.ColaboradorDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/colaboradores")
public class ColaboradorResource {
	
	@Autowired
	private ColaboradorService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<ColaboradorDTO> show(@PathVariable Long id) {
        ColaboradorDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Colaborador não encontrado! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ColaboradorDTO>> findAll(@RequestParam(value="ativo", required=false) Boolean ativo) {
		List<Colaborador> list = service.findAll(ativo);
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	@PostMapping
	public ResponseEntity<ColaboradorDTO> create(@Validated @RequestBody ColaboradorDTO colaboradorDTO){
		Colaborador colaborador = service.fromDTO(colaboradorDTO, new Colaborador());
		service.insert(colaborador);
		return ResponseEntity.created(null).body(convertToDto(colaborador));
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<ColaboradorDTO> update(@Valid @RequestBody ColaboradorDTO colaboradorDTO, @PathVariable Long id) {
		ColaboradorDTO response = service.find(id).map( colaborador -> {
			service.fromDTO(colaboradorDTO, colaborador);
			return convertToDto(service.update(colaborador));
		}).orElseThrow( () -> new ObjectNotFoundException("Colaborador não encontrado! Código: " + id));

		return ResponseEntity.ok().body(response);
	}

	private ColaboradorDTO convertToDto(Colaborador colaborador) {
		return DozerConverter.parseObject(colaborador, ColaboradorDTO.class);
	}

	private List<ColaboradorDTO> convertListToDto(List<Colaborador> colaboradores){
		return DozerConverter.parseListObjects(colaboradores, ColaboradorDTO.class);
	}
}
