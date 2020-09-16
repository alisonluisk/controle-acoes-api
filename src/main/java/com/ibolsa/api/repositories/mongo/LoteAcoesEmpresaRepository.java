package com.ibolsa.api.repositories.mongo;

import com.ibolsa.api.domain.mongo.acoes.Acao;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteAcoesEmpresaRepository extends MongoRepository<Acao, String> {
}
