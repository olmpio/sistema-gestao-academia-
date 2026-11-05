package br.com.academia.repository;

import br.com.academia.model.Exercicio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExercicioRepository extends MongoRepository<Exercicio, String> {
}
package br.com.academia.repository;

import br.com.academia.model.Exercicio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExercicioRepository extends MongoRepository<Exercicio, String> {
}
package br.com.academia.service;

import br.com.academia.model.Exercicio;
import br.com.academia.repository.EquipamentoRepository;
import br.com.academia.repository.ExercicioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {
    private final ExercicioRepository exercicioRepository;
    private final EquipamentoRepository equipamentoRepository;

    public ExercicioService(ExercicioRepository exercicioRepository, EquipamentoRepository equipamentoRepository) {
        this.exercicioRepository = exercicioRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Exercicio> listarTodos() {
        return exercicioRepository.findAll();
    }

    public Optional<Exercicio> buscarPorId(String id) {
        return exercicioRepository.findById(id);
    }

    public Exercicio salvar(Exercicio exercicio) {
        if (exercicio.getEquipamento() != null && exercicio.getEquipamento().getId() != null) {
            if (!equipamentoRepository.existsById(exercicio.getEquipamento().getId())) {
                throw new IllegalArgumentException("Erro de integridade: O equipamento informado não existe.");
            }
        }
        return exercicioRepository.save(exercicio);
    }

    public void deletar(String id) {
        if (!exercicioRepository.existsById(id)) {
            throw new RuntimeException("Exercício não encontrado.");
        }
        exercicioRepository.deleteById(id);
    }
}
