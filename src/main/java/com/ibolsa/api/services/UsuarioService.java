package com.ibolsa.api.services;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import com.ibolsa.api.domain.pg.usuario.Usuario;
import com.ibolsa.api.dto.usuario.UsuarioDTO;
import com.ibolsa.api.exceptions.DataIntegrityException;
import com.ibolsa.api.exceptions.ObjectNotFoundException;
import com.ibolsa.api.repositories.pg.UsuarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private PerfilService perfilService;

	public Optional<Usuario> find(Long id) {
		return repo.findById(id);
	}

	public List<Usuario> findAll(Boolean ativo) {
		return repo.findDistinctByAtivoAndAcionistaNull(ativo);
	}

	public Usuario insert(Usuario usuario) {
		return repo.save(usuario);
	}

	public Usuario update(Usuario usuario) {
		if(usuario == null || usuario.getId() == null)
			throw new DataIntegrityException("Usuário id não pode ser nulo.");

		return repo.save(usuario);
	}

	public void desativarAtivarUsuarioColaborador(Colaborador colaborador, boolean ativar){
		Optional<Usuario> user = repo.findByColaborador(colaborador);
		if(user.isPresent()){
			Usuario usuario = user.get();
			usuario.setAtivo(ativar);
			repo.save(usuario);
		}
	}

	public void desativarAtivarUsuarioAcionista(Acionista acionista, boolean ativar){
		Optional<Usuario> user = repo.findByAcionista(acionista);
		if(user.isPresent()){
			Usuario usuario = user.get();
			usuario.setAtivo(ativar);
			repo.save(usuario);
		}
	}

	public Usuario fromDTO(UsuarioDTO dto, Usuario usuario){
		usuario.setPerfilUsuario(perfilService.find(dto.getCodigoPerfilUsuario()).orElseThrow( () -> new ObjectNotFoundException("Perfil não encontrado! Código: " + dto.getCodigoPerfilUsuario())));

		return usuario;
	}


	public void createUsuarioByColaborador(Colaborador colaborador){
		Usuario usuario = new Usuario();
		usuario.setColaborador(colaborador);
		usuario.setAtivo(true);
		usuario.setUsuario(createUsername(colaborador.getNome()));

		repo.save(usuario);
	}

	public void createUsuarioByAcionista(Acionista acionista){
		Usuario usuario = new Usuario();
		usuario.setAcionista(acionista);
		usuario.setAtivo(true);
		usuario.setPerfilUsuario(perfilService.getPerfilAcionista());
		usuario.setUsuario(createUsername(acionista.getNome()));

		repo.save(usuario);
	}

	public String createUsername(String nome){
		String[] nomes = nome.split(" ");
		if(nomes.length > 1)
			return String.format("%s.%s", nomes[0].toLowerCase(), nomes[nomes.length-1].toLowerCase());
		return nome.toLowerCase();
	}
}
