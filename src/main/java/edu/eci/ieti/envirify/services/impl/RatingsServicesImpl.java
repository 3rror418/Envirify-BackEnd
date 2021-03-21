package edu.eci.ieti.envirify.services.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Rating;
import edu.eci.ieti.envirify.persistence.RatingPersistence;
import edu.eci.ieti.envirify.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RatingsServicesImpl implements RatingServices {
    @Autowired
    private RatingPersistence persistence;
    @Override
    public void addRating(Rating rating, String id) throws EnvirifyException {
        try {
            persistence.addRating(rating, id);
        } catch (EnvirifyPersistenceException e) {
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.NOT_FOUND);
        }
    }
}
