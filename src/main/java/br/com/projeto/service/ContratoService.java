package br.com.academia.service;

import br.com.academia.model.Contrato;
import br.com.academia.repository.AlunoRepository;
import br.com.academia.repository.ContratoRepository;
import br.com.academia.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;

    public ContratoService(ContratoRepository contratoRepository, AlunoRepository alunoRepository, PlanoRepository planoRepository) {
        this.contratoRepository = contratoRepository;
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
    }

    public List<Contrato> listarTodos() {
        return contratoRepository.findAll();
    }

    public Optional<Contrato> buscarPorId(String id) {
        return contratoRepository.findById(id);
    }

    public Contrato salvar(Contrato contrato) {
        if (contrato.getAluno() == null || !alunoRepository.existsById(contrato.getAluno().getId())) {
            throw new IllegalArgumentException("Erro de integridade: O aluno informado não existe.");
        }
        if (contrato.getPlano() == null || !planoRepository.existsById(contrato.getPlano().getId())) {
            throw new IllegalArgumentException("Erro de integridade: O plano informado não existe.");
        }
        return contratoRepository.save(contrato);
    }

    public void deletar(String id) {
        if (!contratoRepository.existsById(id)) {
            throw new RuntimeException("Contrato não encontrado.");
        }
        contratoRepository.deleteById(id);
    }
}
