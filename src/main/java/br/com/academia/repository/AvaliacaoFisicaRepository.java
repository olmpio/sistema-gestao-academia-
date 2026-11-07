package br.com.academia.repository;

import br.com.academia.model.AvaliacaoFisica;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvaliacaoFisicaRepository extends MongoRepository<AvaliacaoFisica, String> {
}
