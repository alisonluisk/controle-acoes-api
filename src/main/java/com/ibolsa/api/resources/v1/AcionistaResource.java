package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import com.ibolsa.api.dto.acionista.AcionistaDTO;
import com.ibolsa.api.dto.colaborador.ColaboradorDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.AcionistaService;
import com.ibolsa.api.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/acionistas")
public class AcionistaResource {
	
	@Autowired
	private AcionistaService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<AcionistaDTO> show(@PathVariable Long id) {
		AcionistaDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Acionista não encontrado! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<AcionistaDTO>> findAll(@RequestParam(value="ativo", required=false, defaultValue = "true") Boolean ativo) {
		List<Acionista> list = service.findAll(ativo);
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	@GetMapping("/pageable")
	public Page<AcionistaDTO> search(@RequestParam(value="ativo", required=false, defaultValue = "true") Boolean ativo,
									 @RequestParam(value="search", required = false) String searchString,
									 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
									 @RequestParam(value = "size", required = false, defaultValue = "10") int size,
									 @RequestParam(value = "sortColumn", required = false, defaultValue = "nome") String sortColumn,
									 @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
		return toPageObjectDto(service.findByParamsPageable(ativo, searchString, page, size, sortColumn, sortDirection));
	}

	@PutMapping(value="/{id}/set_ativo")
	public ResponseEntity<AcionistaDTO> setAtivo(@Valid @RequestBody Boolean ativo, @PathVariable Long id) {
		AcionistaDTO response = service.find(id).map( acionista -> {
			service.desativarAtivarAcionista(acionista, ativo);
			return convertToDto(acionista);
		}).orElseThrow( () -> new ObjectNotFoundException("Acionista não encontrado! Código: " + id));

		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity<AcionistaDTO> create(@Validated @RequestBody AcionistaDTO acionistaDto){
		Acionista acionista = service.fromDTO(acionistaDto, new Acionista());
		service.insert(acionista);
		return ResponseEntity.created(null).body(convertToDto(acionista));
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<AcionistaDTO> update(@Valid @RequestBody AcionistaDTO acionistaDto, @PathVariable Long id) {
		AcionistaDTO response = service.find(id).map( acionista -> {
			service.fromDTO(acionistaDto, acionista);
			return convertToDto(service.update(acionista));
		}).orElseThrow( () -> new ObjectNotFoundException("Acionista não encontrado! Código: " + id));

		return ResponseEntity.ok().body(response);
	}

	private AcionistaDTO convertToDto(Acionista acionista) {
		return DozerConverter.parseObject(acionista, AcionistaDTO.class);
	}

	private List<AcionistaDTO> convertListToDto(List<Acionista> acionistas){
		return DozerConverter.parseListObjects(acionistas, AcionistaDTO.class);
	}

	public Page<AcionistaDTO> toPageObjectDto(Page<Acionista> objects) {
		Page<AcionistaDTO> dtos  = objects.map(this::convertToDto);
		return dtos;
	}


}
