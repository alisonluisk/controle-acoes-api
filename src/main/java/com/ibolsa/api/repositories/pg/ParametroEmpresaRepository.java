package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.empresa.ParametroEmpresa;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface ParametroEmpresaRepository extends JpaRepository<ParametroEmpresa, Long> {

    Optional<ParametroEmpresa> findByEmpresaId(Long empresa);
}
