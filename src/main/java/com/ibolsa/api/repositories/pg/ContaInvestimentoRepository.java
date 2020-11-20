package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.contaInvestimento.ContaInvestimento;
import com.ibolsa.api.domain.pg.empresa.Empresa;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@JaversSpringDataAuditable
public interface ContaInvestimentoRepository extends JpaRepository<ContaInvestimento, Long> {

    List<ContaInvestimento> findByEmpresa(Empresa empresa);

    List<ContaInvestimento> findByAcionista(Acionista acionista);

    @Query("SELECT coalesce(max(d.conta) + 1, 1) as conta "
            + "FROM ContaInvestimento d ")
    Long getNextConta();

}
