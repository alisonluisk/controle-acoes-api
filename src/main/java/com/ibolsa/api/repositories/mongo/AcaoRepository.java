package com.ibolsa.api.repositories.mongo;

import com.ibolsa.api.domain.mongo.acoes.Acao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends MongoRepository<Acao, String> {
}
