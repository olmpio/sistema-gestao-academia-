package br.com.academia.repository;

import br.com.academia.model.Plano;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanoRepository extends MongoRepository<Plano, String> {
}