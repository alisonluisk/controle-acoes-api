package com.ibolsa.api.resources.v1;

import com.ibolsa.api.dto.localizacao.CepDTO;
import com.ibolsa.api.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/v1/cep")
public class CepResource {

	@Autowired
	private CepService serviceCep;

	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ResponseEntity<CepDTO> show(@PathVariable String codigo) {
		CepDTO cep = serviceCep.buscarCep(codigo);

		return ResponseEntity.ok().body(cep);
	}

}
