package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.Rating;

public interface RatingServices {
    void addRating(Rating rating, String id) throws EnvirifyException;
}
