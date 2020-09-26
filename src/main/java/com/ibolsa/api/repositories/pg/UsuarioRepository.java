package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.usuario.Usuario;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findDistinctByAtivoAndAcionistaNull(Boolean ativo);

}
