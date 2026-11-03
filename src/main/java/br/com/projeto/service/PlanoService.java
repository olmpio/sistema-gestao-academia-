package br.com.academia.service;

import br.com.academia.model.Plano;
import br.com.academia.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {
    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<Plano> listarTodos() {
        return planoRepository.findAll();
    }

    public Optional<Plano> buscarPorId(String id) {
        return planoRepository.findById(id);
    }

    public Plano salvar(Plano plano) {
        return planoRepository.save(plano);
    }

    public void deletar(String id) {
        planoRepository.deleteById(id);
    }
}