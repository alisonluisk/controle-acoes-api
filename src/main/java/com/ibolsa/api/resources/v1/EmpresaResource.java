package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.empresa.Empresa;
import com.ibolsa.api.dto.empresa.EmpresaDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<EmpresaDTO> show(@PathVariable Long id) {
        EmpresaDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Empresa não encontrada! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<EmpresaDTO>> findAll() {
		List<Empresa> list = service.findAll();
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	@PostMapping
	public ResponseEntity<EmpresaDTO> create(@Validated @RequestBody EmpresaDTO empresaDTO){
		Empresa empresa = service.fromDTO(empresaDTO, new Empresa());
		service.insert(empresa);
		return ResponseEntity.created(null).body(convertToDto(empresa));
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<EmpresaDTO> update(@Valid @RequestBody EmpresaDTO empresaDTO, @PathVariable Long id) {
		EmpresaDTO response = service.find(id).map( empresa -> {
			service.fromDTO(empresaDTO, empresa);
			return convertToDto(service.update(empresa));
		}).orElseThrow( () -> new ObjectNotFoundException("Empresa não encontrada! Código: " + id));

		return ResponseEntity.ok().body(response);
	}

	private EmpresaDTO convertToDto(Empresa empresa) {
		return DozerConverter.parseObject(empresa, EmpresaDTO.class);
	}

	private List<EmpresaDTO> convertListToDto(List<Empresa> empresas){
		return DozerConverter.parseListObjects(empresas, EmpresaDTO.class);
	}
}
