package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.municipio.Estado;
import com.ibolsa.api.dto.localizacao.EstadoDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<EstadoDTO> show(@PathVariable Long id) {
		EstadoDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Estado não encontrado! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(value="/find_by_sigla", method=RequestMethod.GET)
	public ResponseEntity<EstadoDTO> findBySigla(@RequestParam String sigla) {
		EstadoDTO response = service.findBySigla(sigla).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Estado não encontrado! Sigla: " + sigla));
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = service.findAll();  
		return ResponseEntity.ok().body(convertListToDto(list));
	}
	
	private EstadoDTO convertToDto(Estado estado) {
		return DozerConverter.parseObject(estado, EstadoDTO.class);
	}
	
	private List<EstadoDTO> convertListToDto(List<Estado> estados){
		return DozerConverter.parseListObjects(estados, EstadoDTO.class);
	}
}
