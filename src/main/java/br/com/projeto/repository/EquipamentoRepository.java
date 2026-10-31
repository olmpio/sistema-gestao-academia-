package br.com.academia.repository;

import br.com.academia.model.Equipamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipamentoRepository extends MongoRepository<Equipamento, String> {
}
