package br.com.academia.repository;

import br.com.academia.model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
    // Consulta personalizada para ajudar na regra de negócio de e-mail duplicado
    Optional<Aluno> findByEmail(String email);
}