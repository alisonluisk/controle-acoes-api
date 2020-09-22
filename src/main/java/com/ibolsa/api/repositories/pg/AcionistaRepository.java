package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@JaversSpringDataAuditable
public interface AcionistaRepository extends JpaRepository<Acionista, Long> {

    List<Acionista> findDistinctByAtivo(Boolean ativo);

    Optional<Acionista> findByCpfCnpj(String cpfCnpj);

}
