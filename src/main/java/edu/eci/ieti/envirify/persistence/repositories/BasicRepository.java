package edu.eci.ieti.envirify.persistence.repositories;

import edu.eci.ieti.envirify.model.Greeting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasicRepository extends MongoRepository<Greeting, String> {
    Greeting findByContent(String content);
}
