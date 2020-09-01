package com.ibolsa.api.resources.v1;

import com.ibolsa.api.domain.municipio.Municipio;
import com.ibolsa.api.dto.localizacao.CepDTO;
import com.ibolsa.api.dto.localizacao.MunicipioDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import com.ibolsa.api.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/v1/cep")
public class CepResource {

	@Autowired
	private MunicipioService serviceMunicipio;

	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ResponseEntity<CepDTO> show(@PathVariable String codigo) {
		final String uri = String.format("https://viacep.com.br/ws/%s/json/", codigo);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CepDTO> result = restTemplate.getForEntity(uri, CepDTO.class);

		if(result.getBody().getErro() != null)
			throw new ObjectNotFoundException(String.format("Cep %s não localizado.", codigo));

		CepDTO cep = result.getBody();
		cep.setMunicipio(convertToDto(serviceMunicipio.find(Long.parseLong(cep.getIbge())).get()));

		return ResponseEntity.ok().body(cep);
	}

	private MunicipioDTO convertToDto(Municipio municipio) {
		return DozerConverter.parseObject(municipio, MunicipioDTO.class);
	}
}
