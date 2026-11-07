package br.com.academia.service;

import br.com.academia.model.Professor;
import br.com.academia.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(String id) {
        return professorRepository.findById(id);
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deletar(String id) {
        if (!professorRepository.existsById(id)) {
            throw new RuntimeException("Professor não encontrado para exclusão.");
        }
        professorRepository.deleteById(id);
    }
}
