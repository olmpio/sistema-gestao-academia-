package br.com.academia.repository;

import br.com.academia.model.Pagamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PagamentoRepository extends MongoRepository<Pagamento, String> {

    // Consulta específica: Buscar pagamentos por status
    List<Pagamento> findByStatus(String status);
}
