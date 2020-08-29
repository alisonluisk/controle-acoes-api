package com.ibolsa.api.repositories;

import com.ibolsa.api.domain.municipio.Estado;
import com.ibolsa.api.domain.municipio.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
	List<Municipio> findDistinctByEstado(Estado estado);
	
	List<Municipio> findDistinctByNomeContainingIgnoreCase(String nome);
	
	List<Municipio> findDistinctByNomeContainingIgnoreCaseAndEstado(String nome, Estado estado);
}
