package edu.eci.ieti.envirify.persistence.repositories;

import edu.eci.ieti.envirify.model.Place;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlaceRepository extends MongoRepository<Place, String>  {

    List<Place> findByDirection(String direction);

}
