package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.modeloContrato.ModeloContrato;
import com.ibolsa.api.dto.modeloContrato.ModeloContratoDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.ModeloContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/modelos_contrato")
public class ModeloContratoResource {
	
	@Autowired
	private ModeloContratoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ModeloContratoDTO> show(@PathVariable Long id) {
		ModeloContratoDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Modelo de contrato não encontrado! Código: " + id));

		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ModeloContratoDTO>> findAll(@RequestParam(value="ativo", required=false) Boolean ativo) {
		List<ModeloContrato> list = service.findAll(ativo);
		return ResponseEntity.ok().body(convertListToDto(list));
	}
	
	private ModeloContratoDTO convertToDto(ModeloContrato modeloContrato) {
		return DozerConverter.parseObject(modeloContrato, ModeloContratoDTO.class);
	}
	
	private List<ModeloContratoDTO> convertListToDto(List<ModeloContrato> modelos){
		return DozerConverter.parseListObjects(modelos, ModeloContratoDTO.class);
	}
}
