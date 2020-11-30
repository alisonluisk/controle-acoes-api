package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.dto.dashboard.totalizadorMes.TotalizadorMes;
import com.ibolsa.api.dto.extratos.ExtratoDTO;
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
	public ResponseEntity<List<DadosVenda>> findByParams(@RequestParam(value="codigoAcionista", required=true) Long codigoAcionista, @RequestParam(value="competencia", required=true) Date competencia) {
		List<DadosVenda> lista = service.getAllParams(codigoAcionista, competencia);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "find")
	public ResponseEntity<ExtratoDTO> getExtratoMensal(@RequestParam(value="codigoAcionista", required=true) Long codigoAcionista, @RequestParam(value="competencia", required=true) Date competencia) {
		ExtratoDTO extrato = service.getExtratoMensal(codigoAcionista, competencia);
		return ResponseEntity.ok().body(extrato);
	}

	@GetMapping(value = "total_doze_meses")
	public ResponseEntity<List<TotalizadorMes>> getTotalDozeMeses() {
		List<TotalizadorMes> list = service.getTotalDozeMeses();
		return ResponseEntity.ok().body(list);
	}
}
