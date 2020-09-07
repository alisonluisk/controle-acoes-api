package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.empresa.Empresa;
import com.ibolsa.api.domain.empresa.ParametroEmpresa;
import com.ibolsa.api.dto.empresa.EmpresaApiDTO;
import com.ibolsa.api.dto.empresa.EmpresaDTO;
import com.ibolsa.api.dto.empresa.ParametroEmpresaDTO;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.CepService;
import com.ibolsa.api.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/v1/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService service;

	@Autowired
	private CepService serviceCep;

	@GetMapping(value="/{id}")
	public ResponseEntity<EmpresaDTO> show(@PathVariable Long id) {
        EmpresaDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Empresa não encontrada! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<EmpresaDTO>> findAll(@RequestParam(value="ativo", required=false) Boolean ativo,
													@RequestParam(value="tipoEmpresa", required=false) TipoEmpresaEnum tipoEmpresa) {
		List<Empresa> list = service.findByParams(ativo, tipoEmpresa);
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

	@RequestMapping(value="/{cnpj}/consultar", method=RequestMethod.GET)
	public ResponseEntity<EmpresaApiDTO> consultar_cnpj(@PathVariable String cnpj) {
		final String uri = String.format("https://www.receitaws.com.br/v1/cnpj/%s", cnpj);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<EmpresaApiDTO> result = restTemplate.getForEntity(uri, EmpresaApiDTO.class);

		if(result.getBody() != null  && result.getBody().getStatus() != null && result.getBody().getStatus().equals("ERROR"))
			throw new ObjectNotFoundException(String.format("Cnpj %s não localizado.", cnpj));

		EmpresaApiDTO empresa = result.getBody();
		empresa.setCepObj(serviceCep.buscarCep(empresa.getCep()));

		return ResponseEntity.ok().body(empresa);
	}

	@GetMapping(value="/{id}/parametro_empresa")
	public ResponseEntity<ParametroEmpresaDTO> showParametroEmpresa(@PathVariable Long id) {
		ParametroEmpresaDTO response = service.findParametroEmpresa(id).map(this::convertParametroDto).orElse(this.convertParametroDto(new ParametroEmpresa()));
		return ResponseEntity.ok().body(response);
	}

	@PutMapping(value="/{id}/parametro_empresa")
	public ResponseEntity<ParametroEmpresaDTO> updateParametroEmpresa(@Valid @RequestBody ParametroEmpresaDTO parametroDTO, @PathVariable Long id) {
		ParametroEmpresaDTO response = service.find(id).map( empresa -> convertParametroDto(service.saveOrUpdateParametros(empresa, parametroDTO))).orElseThrow( () -> new ObjectNotFoundException("Empresa não encontrada! Código: " + id));
		return ResponseEntity.ok().body(response);
	}

	private EmpresaDTO convertToDto(Empresa empresa) {
		return DozerConverter.parseObject(empresa, EmpresaDTO.class);
	}

	private List<EmpresaDTO> convertListToDto(List<Empresa> empresas){
		return DozerConverter.parseListObjects(empresas, EmpresaDTO.class);
	}

	private ParametroEmpresaDTO convertParametroDto(ParametroEmpresa parametro){
		return DozerConverter.parseObject(parametro, ParametroEmpresaDTO.class);
	}
}
