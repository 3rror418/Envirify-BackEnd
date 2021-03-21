package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Rating;

public interface RatingPersistence {

    void addRating(Rating rating, String id) throws EnvirifyPersistenceException;
}
