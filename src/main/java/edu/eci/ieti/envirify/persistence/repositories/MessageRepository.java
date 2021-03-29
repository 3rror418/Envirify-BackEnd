package edu.eci.ieti.envirify.persistence.repositories;

import edu.eci.ieti.envirify.model.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    /*@Tailable
    public Flux<Message> findWithTailableCursorByReceiver(String receiver);*/
}