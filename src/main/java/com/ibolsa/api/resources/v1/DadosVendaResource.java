package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.services.DadosVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/v1/dados_venda")
public class DadosVendaResource {
	
	@Autowired
	private DadosVendaService service;

	@GetMapping(value = "find_by_params")
	public ResponseEntity<List<DadosVenda>> findAllAcionistaPeriodo(@RequestParam(value="codigoAcionista", required=true) Long codigoAcionista, @RequestParam(value="competencia", required=true) Date competencia) {
		List<DadosVenda> list = service.findByParams(codigoAcionista, competencia);
		return ResponseEntity.ok().body(list);
	}
}
