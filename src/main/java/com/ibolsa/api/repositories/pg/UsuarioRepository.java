package com.ibolsa.api.repositories.pg;

import com.ibolsa.api.domain.pg.acionista.Acionista;
import com.ibolsa.api.domain.pg.colaborador.Colaborador;
import com.ibolsa.api.domain.pg.usuario.Usuario;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@JaversSpringDataAuditable
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findDistinctByAtivoAndAcionistaNull(Boolean ativo);

    Optional<Usuario> findByColaborador(Colaborador colaborador);

    Optional<Usuario> findByAcionista(Acionista acionista);

    @Query(value = " select u " +
                    "from Usuario u " +
                    "left join Colaborador c on c = u.colaborador " +
                    "where c.email = :email ")
    Optional<Usuario> findByEmailColaborador(String email);
}
