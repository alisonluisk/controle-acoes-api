package com.ibolsa.api.repositories;

import com.ibolsa.api.domain.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    List<Empresa> findDistinctByAtivo(Boolean ativo);

    Optional<Empresa> findByCnpj(String cnpj);
}
