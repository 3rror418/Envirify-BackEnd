package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Place;

/**
 * Place Persistence Methods For Envirify App.
 *
 * @author Error 418
 */
public interface PlacePersistence {

    /**
     * Add a new place to the db
     * @param place the new place
     * @param email the email of the owner
     * @throws EnvirifyPersistenceException When that place already exists or not exist the user.
     */
    void addPlace(Place place, String email) throws EnvirifyPersistenceException;

}
