package com.ibolsa.api.repositories.mongo;

import com.ibolsa.api.domain.mongo.acoes.LoteAcoesEmpresa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteAcoesEmpresaRepository extends MongoRepository<LoteAcoesEmpresa, String> {

}
