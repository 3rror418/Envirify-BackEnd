package edu.eci.ieti.envirify.services.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.persistence.PlacePersistence;
import edu.eci.ieti.envirify.services.PlaceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Class That Implements The Place Methods For Envirify App.
 *
 * @author Error 418
 */
@Service
public class PlaceServicesImpl implements PlaceServices {

    @Autowired
    private PlacePersistence persistence;

    /**
     * add a place of a user
     * @param place the place
     * @param email the owner
     * @throws EnvirifyException When that place already exists or not exist the user.
     */
    @Override
    public void addPlace(Place place, String email) throws EnvirifyException {
        try {
            persistence.addPlace(place,email);
        } catch (EnvirifyPersistenceException e) {
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.CONFLICT);
        }

    }
}
