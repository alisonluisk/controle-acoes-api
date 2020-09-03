package com.ibolsa.api.services;

import com.ibolsa.api.domain.empresa.Empresa;
import com.ibolsa.api.dto.empresa.EmpresaDTO;
import com.ibolsa.api.enums.TipoEmpresaEnum;
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

	public List<Empresa> findByParams(Boolean ativo, TipoEmpresaEnum tipo){
		return repo.findByParams(ativo, tipo);
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
		empresa.setQtdAcoes(dto.getQtdAcoes());
		empresa.setCotasOn(dto.getCotasOn());
		empresa.setCotasPn(dto.getCotasPn());
		empresa.setMunicipio(municipioService.find(dto.getCodigoMunicipio()).orElseThrow( () -> new ObjectNotFoundException("Município não encontrado! Código: " + dto.getCodigoMunicipio())));
		if(dto.getCodigoMatriz() != null)
			empresa.setMatriz(find(dto.getCodigoMatriz()).orElseThrow( () -> new ObjectNotFoundException("Empresa matriz não encontrada! Código: " + dto.getCodigoMatriz())));
		else empresa.setMatriz(null);

		return empresa;
	}
}
