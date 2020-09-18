package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.empresa.Empresa;
import com.ibolsa.api.domain.pg.empresa.ParametroEmpresa;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface ParametroEmpresaRepository extends JpaRepository<ParametroEmpresa, Long> {

    Optional<ParametroEmpresa> findByEmpresaId(Long empresa);

    @Query("select p from ParametroEmpresa p join Empresa e on e = p.empresa where e.ativo = true " +
            "order by e.id")
    List<ParametroEmpresa> findAllParametroEmpresas();
}
