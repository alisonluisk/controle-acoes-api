package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    List<Colaborador> findDistinctByAtivo(Boolean ativo);

    Optional<Colaborador> findByCpf(String cpf);

}
