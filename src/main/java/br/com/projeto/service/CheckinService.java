package br.com.academia.service;

import br.com.academia.model.Checkin;
import br.com.academia.repository.AlunoRepository;
import br.com.academia.repository.CheckinRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CheckinService {
    private final CheckinRepository checkinRepository;
    private final AlunoRepository alunoRepository;

    public CheckinService(CheckinRepository checkinRepository, AlunoRepository alunoRepository) {
        this.checkinRepository = checkinRepository;
        this.alunoRepository = alunoRepository;
    }

    public List<Checkin> listarTodos() {
        return checkinRepository.findAll();
    }

    public Optional<Checkin> buscarPorId(String id) {
        return checkinRepository.findById(id);
    }

    public Checkin salvar(Checkin checkin) {
        if (checkin.getAluno() == null || !alunoRepository.existsById(checkin.getAluno().getId())) {
            throw new IllegalArgumentException("Erro de integridade: Aluno do check-in não encontrado.");
        }
        return checkinRepository.save(checkin);
    }

    public void deletar(String id) {
        if (!checkinRepository.existsById(id)) {
            throw new RuntimeException("Check-in não encontrado.");
        }
        checkinRepository.deleteById(id);
    }
}
