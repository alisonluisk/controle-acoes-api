package com.ibolsa.api.repositories;

import com.ibolsa.api.domain.empresa.ParametroEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroEmpresaRepository extends JpaRepository<ParametroEmpresa, Long> {

    Optional<ParametroEmpresa> findByEmpresaId(Long empresa);
}
