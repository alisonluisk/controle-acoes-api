package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.empresa.Empresa;
import com.ibolsa.api.domain.pg.empresa.ParametroEmpresa;
import com.ibolsa.api.dto.empresa.EmpresaDTO;
import com.ibolsa.api.dto.empresa.ParametroEmpresaDTO;
import com.ibolsa.api.enums.StatusAcoesEmpresaEnum;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import com.ibolsa.api.exceptions.DataIntegrityException;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.repositories.pg.EmpresaRepository;
import com.ibolsa.api.repositories.pg.ParametroEmpresaRepository;
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

	@Autowired
	private ParametroEmpresaRepository parametroRepository;
	
	public Optional<Empresa> find(Long id) {
		return repo.findById(id);
	}

	public Optional<ParametroEmpresa> findParametroEmpresa(Long empresaId){
		return parametroRepository.findByEmpresaId(empresaId);
	}

	public Optional<Empresa> findByCnpj(String cnpj){ return repo.findByCnpj(cnpj); }

	public List<Empresa> findByParams(Boolean ativo, TipoEmpresaEnum tipo){
		return repo.findByParams(ativo, tipo);
	}

	public List<Empresa> findAllEmpresasAcoes(){ return repo.findAllEmpresasAcoes(); }

	public List<ParametroEmpresa> findAllParametroEmpresas(){ return parametroRepository.findAllParametroEmpresas(); }

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
		empresa.setAtivo(dto.getAtivo());
		empresa.setMunicipio(municipioService.find(dto.getCodigoMunicipio()).orElseThrow( () -> new ObjectNotFoundException("Município não encontrado! Código: " + dto.getCodigoMunicipio())));
		if(dto.getCodigoMatriz() != null)
			empresa.setMatriz(find(dto.getCodigoMatriz()).orElseThrow( () -> new ObjectNotFoundException("Empresa matriz não encontrada! Código: " + dto.getCodigoMatriz())));
		else empresa.setMatriz(null);

//		Seta o tipo somente se status ainda é aguardando ou que não tem acoes
		if(empresa.getStatusAcoes().equals(StatusAcoesEmpresaEnum.SEM_ACOES) ||
				empresa.getStatusAcoes().equals(StatusAcoesEmpresaEnum.AGUARDANDO)){
			if(empresa.getTipoEmpresa().equals(TipoEmpresaEnum.HOLDING))
				empresa.setStatusAcoes(StatusAcoesEmpresaEnum.SEM_ACOES);
			else empresa.setStatusAcoes(StatusAcoesEmpresaEnum.AGUARDANDO);
		}

		return empresa;
	}

	public ParametroEmpresa saveOrUpdateParametros(Empresa empresa, ParametroEmpresaDTO parametroDTO) {
		ParametroEmpresa parametro = parametroRepository.findByEmpresaId(empresa.getId()).orElse(new ParametroEmpresa());
		parametro.setCotasOn(parametroDTO.getCotasOn());
		parametro.setCotasPn(parametroDTO.getCotasPn());
		parametro.setEmpresa(empresa);

		parametroRepository.save(parametro);

		return parametro;
	}
}
