package br.com.academia.repository;

import br.com.academia.model.Treino;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TreinoRepository extends MongoRepository<Treino, String> {

    // Consulta específica: Buscar treinos por objetivo
    List<Treino> findByObjetivoContainingIgnoreCase(String objetivo);
}