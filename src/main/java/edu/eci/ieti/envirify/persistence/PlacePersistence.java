package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Place;


public interface PlacePersistence {

    void addPlace(Place place, String email) throws EnvirifyPersistenceException;

}
