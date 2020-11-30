package com.ibolsa.api.repositories.mongo;

import com.ibolsa.api.domain.mongo.dadosVenda.DadosVenda;
import com.ibolsa.api.repositories.mongo.Impl.DadosVendaRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DadosVendaRepository extends MongoRepository<DadosVenda, String>, DadosVendaRepositoryCustom {

    List<DadosVenda> findByCodigoAcionistaOrderByDataVenda(Long codigoAcionista);

    List<DadosVenda> findByCpfCnpjClienteAndCodigoConvenioAndCodigoAcionistaIsNull(String cpfCnpj, Long codigoConvenio);
}
