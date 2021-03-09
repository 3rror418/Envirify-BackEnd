package edu.eci.ieti.envirify.services.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.persistence.PlacePersistence;
import edu.eci.ieti.envirify.services.PlaceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PlaceServicesImpl implements PlaceServices {

    @Autowired
    private PlacePersistence persistence;

    @Override
    public void addPlace(Place place, String email) throws EnvirifyException {
        try {
            persistence.addPlace(place,email);
        } catch (EnvirifyPersistenceException e) {
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.CONFLICT);
        }

    }
}
