package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import com.ibolsa.api.domain.pg.municipio.Municipio;
import com.ibolsa.api.dto.colaborador.ColaboradorDTO;
import com.ibolsa.api.dto.empresa.EmpresaDTO;
import com.ibolsa.api.enums.EstadoCivilEnum;
import com.ibolsa.api.enums.StatusAcoesEmpresaEnum;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import com.ibolsa.api.exceptions.DataIntegrityException;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.repositories.pg.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository repo;

	@Autowired
	private MunicipioService municipioService;

	@Autowired
	private UsuarioService usuarioService;

	public Optional<Colaborador> find(Long id) {
		return repo.findById(id);
	}

	public Optional<Colaborador> findByCpf(String cnpj){ return repo.findByCpf(cnpj); }

	public List<Colaborador> findAll(Boolean ativo) {
		return repo.findDistinctByAtivo(ativo);
	}

	public Page<Colaborador> findByParamsPageable(Boolean ativo, String search, int page, int size, String sortColumn, String sortDirection) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortColumn);
		return repo.findByParamsPageable(ativo, search, pageRequest);
	}

	public void desativarAtivarColaborador(Colaborador colaborador, boolean ativar) {
		colaborador.setAtivo(ativar);
		repo.save(colaborador);
		usuarioService.desativarAtivarUsuarioColaborador(colaborador, ativar);
	}

	public Colaborador insert(Colaborador colaborador) {
		if(findByCpf(colaborador.getCpf()).isPresent())
			throw new DataIntegrityException("CPF já cadastrado.");

		repo.save(colaborador);

		usuarioService.createUsuarioByColaborador(colaborador);

		return colaborador;
	}

	public Colaborador update(Colaborador colaborador) {
		if(colaborador == null || colaborador.getId() == null)
			throw new DataIntegrityException("Colaborador id não pode ser nulo.");

		return repo.save(colaborador);
	}

	public Colaborador fromDTO(ColaboradorDTO dto, Colaborador colaborador){
		colaborador.setCpf(dto.getCpf());
		colaborador.setBairro(dto.getBairro());
		colaborador.setCep(dto.getCep());
		colaborador.setComplemento(dto.getComplemento());
		colaborador.setDataNascimento(dto.getDataNascimento());
		colaborador.setEmail(dto.getEmail());
		colaborador.setLogradouro(dto.getLogradouro());
		colaborador.setNome(dto.getNome());
		colaborador.setNumero(dto.getNumero());
		colaborador.setTelefoneFixo(dto.getTelefoneFixo());
		colaborador.setTelefoneCelular(dto.getTelefoneCelular());
		colaborador.setEstadoCivil(dto.getEstadoCivil());
		colaborador.setAtivo(dto.getAtivo());
		colaborador.setRg(dto.getRg());
		colaborador.setMunicipio(municipioService.find(dto.getCodigoMunicipio()).orElseThrow( () -> new ObjectNotFoundException("Município não encontrado! Código: " + dto.getCodigoMunicipio())));

		return colaborador;
	}
}
