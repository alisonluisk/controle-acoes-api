package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.colaborador.Colaborador;
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
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    List<Colaborador> findDistinctByAtivo(Boolean ativo);

    Optional<Colaborador> findByCpf(String cpf);

    @Query("SELECT d "
            + "FROM Colaborador d "
            + "LEFT JOIN Municipio municipio on municipio = d.municipio "
            + "WHERE (:search is null or (CAST(d.id as string) like CONCAT('%',lower(:search),'%') or lower(d.cpf) like CONCAT('%',lower(:search),'%') or lower(d.nome) like CONCAT('%',lower(:search),'%') or lower(municipio.nome) like CONCAT('%',lower(:search),'%'))) "
            + "  AND (:ativo is null or d.ativo = :ativo) ")
    Page<Colaborador> findByParamsPageable(Boolean ativo, String search, Pageable pageable);

}
