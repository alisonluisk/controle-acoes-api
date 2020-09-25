package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.usuario.PerfilUsuario;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface PerfilRepository extends JpaRepository<PerfilUsuario, Long> {

}
