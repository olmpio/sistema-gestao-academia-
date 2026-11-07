package br.com.academia.controller;

import br.com.academia.model.AvaliacaoFisica;
import br.com.academia.service.AvaliacaoFisicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoFisicaController {
    private final AvaliacaoFisicaService service;

    public AvaliacaoFisicaController(AvaliacaoFisicaService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<AvaliacaoFisica>> listar() { return ResponseEntity.ok(service.listarTodos()); }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody AvaliacaoFisica avaliacao) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(avaliacao));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody AvaliacaoFisica avaliacao) {
        try {
            avaliacao.setId(id);
            return ResponseEntity.ok(service.salvar(avaliacao));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
