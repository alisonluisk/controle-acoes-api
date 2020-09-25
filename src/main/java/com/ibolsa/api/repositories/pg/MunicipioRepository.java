package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.municipio.Estado;
import com.ibolsa.api.domain.pg.municipio.Municipio;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
	List<Municipio> findDistinctByEstado(Estado estado);

	@Query(nativeQuery = true, value = "SELECT * FROM municipio "
			+ "WHERE unaccent(nome) ilike unaccent(CONCAT('%',:nome,'%'))")
	List<Municipio> findAllByNome(String nome);

	List<Municipio> findDistinctByNomeContainingIgnoreCaseAndEstado(String nome, Estado estado);
}
