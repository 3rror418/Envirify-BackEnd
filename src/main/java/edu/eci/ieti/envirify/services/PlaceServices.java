package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.Place;

/**
 * Place Methods For Envirify App.
 *
 * @author Error 418
 */
public interface PlaceServices {

    /**
     * add a place of a user
     * @param place the place
     * @param email the owner
     * @throws EnvirifyException When that place already exists or not exist the user.
     */
    void addPlace(Place place, String email) throws EnvirifyException;
}
