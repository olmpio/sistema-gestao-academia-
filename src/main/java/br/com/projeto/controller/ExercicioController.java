package br.com.academia.controller;

import br.com.academia.model.Exercicio;
import br.com.academia.service.ExercicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exercicios")
public class ExercicioController {
    private final ExercicioService service;

    public ExercicioController(ExercicioService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Exercicio>> listar() { return ResponseEntity.ok(service.listarTodos()); }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Exercicio exercicio) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(exercicio));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody Exercicio exercicio) {
        try {
            exercicio.setId(id);
            return ResponseEntity.ok(service.salvar(exercicio));
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
