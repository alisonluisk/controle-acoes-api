package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.modeloContrato.ModeloContrato;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface ModeloContratoRepository extends JpaRepository<ModeloContrato, Long> {

    List<ModeloContrato> findByAtivo(Boolean ativo);

}
