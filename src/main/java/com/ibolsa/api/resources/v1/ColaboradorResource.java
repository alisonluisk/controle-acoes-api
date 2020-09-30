package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import com.ibolsa.api.dto.acionista.AcionistaDTO;
import com.ibolsa.api.dto.colaborador.ColaboradorDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	@GetMapping("/pageable")
	public Page<ColaboradorDTO> search(@RequestParam(value="ativo", required=false, defaultValue = "true") Boolean ativo,
									 @RequestParam(value="search", required = false) String searchString,
									 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
									 @RequestParam(value = "size", required = false, defaultValue = "10") int size,
									 @RequestParam(value = "sortColumn", required = false, defaultValue = "nome") String sortColumn,
									 @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
		return toPageObjectDto(service.findByParamsPageable(ativo, searchString, page, size, sortColumn, sortDirection));
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

	@PutMapping(value="/{id}/set_ativo")
	public ResponseEntity<ColaboradorDTO> setAtivo(@Valid @RequestBody Boolean ativo, @PathVariable Long id) {
		ColaboradorDTO response = service.find(id).map( colaborador -> {
			service.desativarAtivarColaborador(colaborador, ativo);
			return convertToDto(colaborador);
		}).orElseThrow( () -> new ObjectNotFoundException("Colaborador não encontrado! Código: " + id));

		return ResponseEntity.ok().body(response);
	}

	private ColaboradorDTO convertToDto(Colaborador colaborador) {
		return DozerConverter.parseObject(colaborador, ColaboradorDTO.class);
	}

	private List<ColaboradorDTO> convertListToDto(List<Colaborador> colaboradores){
		return DozerConverter.parseListObjects(colaboradores, ColaboradorDTO.class);
	}

	public Page<ColaboradorDTO> toPageObjectDto(Page<Colaborador> objects) {
		Page<ColaboradorDTO> dtos  = objects.map(this::convertToDto);
		return dtos;
	}

}
