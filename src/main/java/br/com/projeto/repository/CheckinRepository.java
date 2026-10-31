package br.com.academia.repository;

import br.com.academia.model.Checkin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckinRepository extends MongoRepository<Checkin, String> {
}
