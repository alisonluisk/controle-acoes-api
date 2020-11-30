package com.ibolsa.api.resources.v1;

import com.ibolsa.api.dto.dashboard.totalizadorMes.TotalizadorMes;
import com.ibolsa.api.services.DadosVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/v1/dashboards_farmacia")
public class DashboardVendasFarmaciaService {
	
	@Autowired
	private DadosVendaService service;

	@GetMapping(value = "get_last_doze_meses")
	public ResponseEntity<List<TotalizadorMes>> getTotalLastDozeMeses() {
		List<TotalizadorMes> list = service.getTotalDozeMeses();
		return ResponseEntity.ok().body(list);
	}
}
