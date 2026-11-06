package br.com.academia.service;

import br.com.academia.model.Treino;
import br.com.academia.repository.AlunoRepository;
import br.com.academia.repository.ProfessorRepository;
import br.com.academia.repository.TreinoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {
    private final TreinoRepository treinoRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public TreinoService(TreinoRepository treinoRepository, AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.treinoRepository = treinoRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public List<Treino> listarTodos() {
        return treinoRepository.findAll();
    }

    public Optional<Treino> buscarPorId(String id) {
        return treinoRepository.findById(id);
    }

    public Treino salvar(Treino treino) {
        if (treino.getAluno() == null || !alunoRepository.existsById(treino.getAluno().getId())) {
            throw new IllegalArgumentException("Erro de integridade: Aluno do treino não encontrado.");
        }
        if (treino.getProfessor() == null || !professorRepository.existsById(treino.getProfessor().getId())) {
            throw new IllegalArgumentException("Erro de integridade: Professor responsável não encontrado.");
        }
        return treinoRepository.save(treino);
    }

    public void deletar(String id) {
        if (!treinoRepository.existsById(id)) {
            throw new RuntimeException("Treino não encontrado.");
        }
        treinoRepository.deleteById(id);
    }
}
