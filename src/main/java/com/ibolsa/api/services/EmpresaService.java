package com.ibolsa.api.services;

import com.ibolsa.api.domain.empresa.Empresa;
import com.ibolsa.api.dto.empresa.EmpresaDTO;
import com.ibolsa.api.exceptions.DataIntegrityException;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repo;

	@Autowired
	private MunicipioService municipioService;
	
	public Optional<Empresa> find(Long id) {
		return repo.findById(id);
	}

	public Optional<Empresa> findByCnpj(String cnpj){ return repo.findByCnpj(cnpj); }
	
	public List<Empresa> findAll(Boolean ativo) {
		if(ativo == null)
			return repo.findAll();
		return repo.findDistinctByAtivo(ativo);
	}

	public Empresa insert(Empresa empresa) {
		if(findByCnpj(empresa.getCnpj()).isPresent())
			throw new DataIntegrityException("Cnpj já cadastrado.");

		return repo.save(empresa);
	}

	public Empresa update(Empresa empresa) {
		if(empresa == null || empresa.getId() == null)
			throw new DataIntegrityException("Empresa id não pode ser nulo.");

		return repo.save(empresa);
	}


	public Empresa fromDTO(EmpresaDTO dto, Empresa empresa){
		empresa.setCnpj(dto.getCnpj());
		empresa.setBairro(dto.getBairro());
		empresa.setCep(dto.getCep());
		empresa.setComplemento(dto.getComplemento());
		empresa.setDataAbertura(dto.getDataAbertura());
		empresa.setEmail(dto.getEmail());
		empresa.setLogradouro(dto.getLogradouro());
		empresa.setNomeFantasia(dto.getNomeFantasia());
		empresa.setNumero(dto.getNumero());
		empresa.setRazaoSocial(dto.getRazaoSocial());
		empresa.setTelefone(dto.getTelefone());
		empresa.setTipoEmpresa(dto.getTipoEmpresa());
		empresa.setMunicipio(municipioService.find(dto.getCodigoMunicipio()).orElseThrow( () -> new ObjectNotFoundException("Município não encontrado! Código: " + dto.getCodigoMunicipio())));

		return empresa;
	}
}
