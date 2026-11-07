package br.com.academia.controller;

import br.com.academia.model.Professor;
import br.com.academia.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
    private final ProfessorService service;

    public ProfessorController(ProfessorService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Professor>> listar() { return ResponseEntity.ok(service.listarTodos()); }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Professor> criar(@RequestBody Professor professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable String id, @RequestBody Professor professor) {
        professor.setId(id);
        return ResponseEntity.ok(service.salvar(professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
