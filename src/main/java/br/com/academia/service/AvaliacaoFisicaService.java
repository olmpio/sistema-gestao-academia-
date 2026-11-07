package br.com.academia.service;

import br.com.academia.model.AvaliacaoFisica;
import br.com.academia.repository.AlunoRepository;
import br.com.academia.repository.AvaliacaoFisicaRepository;
import br.com.academia.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoFisicaService {
    private final AvaliacaoFisicaRepository avaliacaoFisicaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public AvaliacaoFisicaService(AvaliacaoFisicaRepository avaliacaoFisicaRepository, AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.avaliacaoFisicaRepository = avaliacaoFisicaRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public List<AvaliacaoFisica> listarTodos() {
        return avaliacaoFisicaRepository.findAll();
    }

    public Optional<AvaliacaoFisica> buscarPorId(String id) {
        return avaliacaoFisicaRepository.findById(id);
    }

    public AvaliacaoFisica salvar(AvaliacaoFisica avaliacao) {
        if (avaliacao.getAluno() == null || !alunoRepository.existsById(avaliacao.getAluno().getId())) {
            throw new IllegalArgumentException("Erro de integridade: Aluno da avaliação não encontrado.");
        }
        if (avaliacao.getProfessor() == null || !professorRepository.existsById(avaliacao.getProfessor().getId())) {
            throw new IllegalArgumentException("Erro de integridade: Professor avaliador não encontrado.");
        }
        return avaliacaoFisicaRepository.save(avaliacao);
    }

    public void deletar(String id) {
        if (!avaliacaoFisicaRepository.existsById(id)) {
            throw new RuntimeException("Avaliação física não encontrada.");
        }
        avaliacaoFisicaRepository.deleteById(id);
    }
}
