package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.municipio.Estado;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findBySigla(String sigla);
}
