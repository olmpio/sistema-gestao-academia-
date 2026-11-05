package br.com.academia.service;

import br.com.academia.model.Pagamento;
import br.com.academia.repository.ContratoRepository;
import br.com.academia.repository.PagamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final ContratoRepository contratoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, ContratoRepository contratoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.contratoRepository = contratoRepository;
    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> buscarPorId(String id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento salvar(Pagamento pagamento) {
        if (pagamento.getContrato() == null || !contratoRepository.existsById(pagamento.getContrato().getId())) {
            throw new IllegalArgumentException("Erro de integridade: Contrato associado ao pagamento não existe.");
        }
        return pagamentoRepository.save(pagamento);
    }

    public void deletar(String id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new RuntimeException("Pagamento não encontrado.");
        }
        pagamentoRepository.deleteById(id);
    }
}
