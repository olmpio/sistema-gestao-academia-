package br.com.academia.service;

import br.com.academia.model.Aluno;
import br.com.academia.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(String id) {
        return alunoRepository.findById(id);
    }

    public Aluno salvar(Aluno aluno) {
        Optional<Aluno> existente = alunoRepository.findByEmail(aluno.getEmail());
        if (existente.isPresent() && (aluno.getId() == null || !existente.get().getId().equals(aluno.getId()))) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com este e-mail.");
        }
        return alunoRepository.save(aluno);
    }

    public void deletar(String id) {
        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado para exclusão.");
        }
        alunoRepository.deleteById(id);
    }
}