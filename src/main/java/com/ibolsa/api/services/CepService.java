package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.municipio.Municipio;
import com.ibolsa.api.dto.localizacao.CepDTO;
import com.ibolsa.api.dto.localizacao.MunicipioDTO;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.helper.DozerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    @Autowired
    private MunicipioService serviceMunicipio;

    public CepDTO buscarCep(String codigo){
        codigo = codigo.replaceAll("\\D+","");
        final String uri = String.format("https://viacep.com.br/ws/%s/json/", codigo);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CepDTO> result = restTemplate.getForEntity(uri, CepDTO.class);

        if(result.getBody().getErro() != null)
            throw new ObjectNotFoundException(String.format("Cep %s não localizado.", codigo));

        CepDTO cep = result.getBody();
        cep.setMunicipio(convertToDto(serviceMunicipio.find(Long.parseLong(cep.getIbge())).get()));

        return cep;
    }

    private MunicipioDTO convertToDto(Municipio municipio) {
        return DozerConverter.parseObject(municipio, MunicipioDTO.class);
    }
}

