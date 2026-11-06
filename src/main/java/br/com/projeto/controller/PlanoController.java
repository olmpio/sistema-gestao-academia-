package br.com.academia.controller;

import br.com.academia.model.Plano;
import br.com.academia.service.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {
    private final PlanoService service;

    public PlanoController(PlanoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Plano>> listar() { return ResponseEntity.ok(service.listarTodos()); }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plano> criar(@RequestBody Plano plano) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(plano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plano> atualizar(@PathVariable String id, @RequestBody Plano plano) {
        plano.setId(id);
        return ResponseEntity.ok(service.salvar(plano));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}