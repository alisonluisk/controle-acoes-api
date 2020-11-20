package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.contaInvestimento.ContaInvestimento;
import com.ibolsa.api.domain.pg.empresa.Empresa;
import com.ibolsa.api.dto.contaInvestimento.ContaInvestimentoDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.AcionistaService;
import com.ibolsa.api.services.ContaInvestimentoService;
import com.ibolsa.api.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/v1/contas_investimento")
public class ContaInvestimentoResource {
	
	@Autowired
	private ContaInvestimentoService service;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private AcionistaService acionistaService;

	@GetMapping(value="/{id}")
	public ResponseEntity<ContaInvestimentoDTO> show(@PathVariable Long id) {
		ContaInvestimentoDTO response = service.find(id).map(this::convertToDto).orElseThrow( () -> new ObjectNotFoundException("Conta de investimento não encontrada! Código: " + id));
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<ContaInvestimentoDTO>> findAll() {
		List<ContaInvestimento> list = service.findAll();
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	@GetMapping(value="/find_all_acionista")
	public ResponseEntity<List<ContaInvestimentoDTO>> findAllAcionista(@RequestParam(value="codigoAcionista", required=true) Long codigoAcionista) {
		Acionista acionista = acionistaService.find(codigoAcionista).orElseThrow( () -> new ObjectNotFoundException("Acionista não encontrado! Código: " + codigoAcionista));
		List<ContaInvestimento> list = service.findByAcionista(acionista);
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	@GetMapping(value="/find_all_empresa")
	public ResponseEntity<List<ContaInvestimentoDTO>> findAllEmpresa(@RequestParam(value="codigoEmpresa", required=true) Long codigoEmpresa) {
		Empresa empresa = empresaService.find(codigoEmpresa).orElseThrow( () -> new ObjectNotFoundException("Empresa não encontrada! Código: " + codigoEmpresa));
		List<ContaInvestimento> list = service.findByEmpresa(empresa);
		return ResponseEntity.ok().body(convertListToDto(list));
	}

	@PostMapping
	public ResponseEntity<ContaInvestimentoDTO> create(@Validated @RequestBody ContaInvestimentoDTO contaInvestimentoDTO){
		ContaInvestimento contaInvestimento = service.fromDTO(contaInvestimentoDTO, new ContaInvestimento());
		service.insert(contaInvestimento);
		return ResponseEntity.created(null).body(convertToDto(contaInvestimento));
	}

	private ContaInvestimentoDTO convertToDto(ContaInvestimento contaInvestimento) {
		return DozerConverter.parseObject(contaInvestimento, ContaInvestimentoDTO.class);
	}

	private List<ContaInvestimentoDTO> convertListToDto(List<ContaInvestimento> contaInvestimentos){
		return DozerConverter.parseListObjects(contaInvestimentos, ContaInvestimentoDTO.class);
	}

}
