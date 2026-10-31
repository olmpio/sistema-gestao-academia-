package br.com.academia.repository;

import br.com.academia.model.Contrato;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContratoRepository extends MongoRepository<Contrato, String> {
}
