package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Place;

import java.util.List;

/**
 * Place Persistence Methods For Envirify App.
 *
 * @author Error 418
 */
public interface PlacePersistence {

    /**
     * Add a new place to the db
     *
     * @param place the new place
     * @param email the email of the owner
     * @throws EnvirifyPersistenceException When that place already exists or not exist the user.
     */
    void addPlace(Place place, String email) throws EnvirifyPersistenceException;

    /**
     * Gets the places based on a city name.
     *
     * @param city The City Name to search.
     * @return A List With The Places that are in the search term city.
     */
    List<Place> getPlacesByCity(String city);

    /**
     * Gets the places based on a department name.
     *
     * @param department The Department Name to search.
     * @return A List With The Places that are in the search term department.
     */
    List<Place> getPlacesByDepartment(String department);
}
