package edu.eci.ieti.envirify.persistence.repositories;

import edu.eci.ieti.envirify.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
