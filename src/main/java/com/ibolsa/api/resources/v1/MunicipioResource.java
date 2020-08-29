package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.municipio.Estado;
import com.ibolsa.api.domain.municipio.Municipio;
import com.ibolsa.api.dto.localizacao.MunicipioDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.EstadoService;
import com.ibolsa.api.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/municipios")
public class MunicipioResource {
	
	@Autowired
	private MunicipioService service;
	
	@Autowired
	private EstadoService serviceEstado;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<MunicipioDTO> show(@PathVariable Long id) {
		MunicipioDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Municipio não encontrado! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MunicipioDTO>> findAll() {
		List<Municipio> list = service.findAll();    
		return ResponseEntity.ok().body(convertListToDto(list));
	}
	
	@RequestMapping(value="find_all_by_estado", method=RequestMethod.GET)
	public ResponseEntity<List<MunicipioDTO>> findAllByEstado(@RequestParam Long codigoEstado) {
		Estado estado = serviceEstado.find(codigoEstado).orElseThrow( () -> new ObjectNotFoundException("Estado não encontrado! Código: " + codigoEstado));
		
		List<Municipio> list = service.findAllMunicipiosByEstado(estado);    
		return ResponseEntity.ok().body(convertListToDto(list));
	}
	
	@RequestMapping(value="find_all_by_nome", method=RequestMethod.GET)
	public ResponseEntity<List<MunicipioDTO>> findAllByNome(@RequestParam(value="nome") String nome) {
		List<Municipio> list = service.findAllMunicipiosByNome(nome);    
		return ResponseEntity.ok().body(convertListToDto(list));
	}
	
	@RequestMapping(value="find_all_by_nome_and_estado", method=RequestMethod.GET)
	public ResponseEntity<List<MunicipioDTO>> findAllByNomeAndEstado(@RequestParam String nome,
			@RequestParam Long codigoEstado) {
		Estado estado = serviceEstado.find(codigoEstado).orElseThrow( () -> new ObjectNotFoundException("Estado não encontrado! Código: " + codigoEstado));
		
		List<Municipio> list = service.findAllMunicipiosByNomeAndEstado(nome, estado);    
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	private MunicipioDTO convertToDto(Municipio municipio) {
		return DozerConverter.parseObject(municipio, MunicipioDTO.class);
	}

	private List<MunicipioDTO> convertListToDto(List<Municipio> municipios){
		return DozerConverter.parseListObjects(municipios, MunicipioDTO.class);
	}
}
