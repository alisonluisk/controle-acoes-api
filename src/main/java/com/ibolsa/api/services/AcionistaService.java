package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.dto.acionista.AcionistaDTO;
import com.ibolsa.api.exceptions.DataIntegrityException;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.repositories.pg.AcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcionistaService {
	
	@Autowired
	private AcionistaRepository repo;

	@Autowired
	private MunicipioService municipioService;

	public Optional<Acionista> find(Long id) {
		return repo.findById(id);
	}

	public Optional<Acionista> findByCpfCnpj(String cpfCnpj){ return repo.findByCpfCnpj(cpfCnpj); }

	public List<Acionista> findAll(Boolean ativo) {
		return repo.findDistinctByAtivo(ativo);
	}

	public Acionista insert(Acionista acionista) {
		if(findByCpfCnpj(acionista.getCpfCnpj()).isPresent())
			throw new DataIntegrityException("CPF/CNPJ já cadastrado.");

		acionista.setConta(repo.getNextConta());
		return repo.save(acionista);
	}

	public Acionista update(Acionista acionista) {
		if(acionista == null || acionista.getId() == null)
			throw new DataIntegrityException("Acionista id não pode ser nulo.");

		return repo.save(acionista);
	}

	public Acionista fromDTO(AcionistaDTO dto, Acionista acionista){
		acionista.setCpfCnpj(dto.getCpfCnpj());
		acionista.setBairro(dto.getBairro());
		acionista.setCep(dto.getCep());
		acionista.setComplemento(dto.getComplemento());
		acionista.setDataNascimento(dto.getDataNascimento());
		acionista.setEmail(dto.getEmail());
		acionista.setLogradouro(dto.getLogradouro());
		acionista.setNome(dto.getNome());
		acionista.setNumero(dto.getNumero());
		acionista.setTelefoneFixo(dto.getTelefoneFixo());
		acionista.setTelefoneCelular(dto.getTelefoneCelular());
		acionista.setEstadoCivil(dto.getEstadoCivil());
		acionista.setAtivo(dto.getAtivo());
		acionista.setRgInscricao(dto.getRgInscricao());
		acionista.setMunicipio(municipioService.find(dto.getCodigoMunicipio()).orElseThrow( () -> new ObjectNotFoundException("Município não encontrado! Código: " + dto.getCodigoMunicipio())));
		acionista.setConta(dto.getConta());
		acionista.setRepresentante(dto.getRepresentante());
		acionista.setCpfRepresentante(dto.getCpfRepresentante());
		acionista.setBanco(dto.getBanco());
		acionista.setAgencia(dto.getAgencia());
		acionista.setNumeroConta(dto.getNumeroConta());
		acionista.setCpfContaBanco(dto.getCpfContaBanco());
		acionista.setNomeContaBanco(dto.getNomeContaBanco());

		return acionista;
	}
}
