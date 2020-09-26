package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@JaversSpringDataAuditable
public interface AcionistaRepository extends JpaRepository<Acionista, Long> {

    List<Acionista> findByAtivo(Boolean ativo);

    Optional<Acionista> findByCpfCnpj(String cpfCnpj);

    @Query("SELECT coalesce(max(d.conta) + 1, 1) as conta "
            + "FROM Acionista d ")
    Long getNextConta();

    Page<Acionista> findDistinctByAtivo(Boolean ativo, Pageable pageable);

}
