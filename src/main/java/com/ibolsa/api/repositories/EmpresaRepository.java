package com.ibolsa.api.repositories;

import com.ibolsa.api.domain.empresa.Empresa;
import com.ibolsa.api.enums.TipoEmpresaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("select e from Empresa e where (:ativo is null or e.ativo = :ativo) and (:tipoEmpresa is null or e.tipoEmpresa = :tipoEmpresa)")
    List<Empresa> findByParams(Boolean ativo, TipoEmpresaEnum tipoEmpresa);

    List<Empresa> findDistinctByAtivo(Boolean ativo);

    Optional<Empresa> findByCnpj(String cnpj);
}
