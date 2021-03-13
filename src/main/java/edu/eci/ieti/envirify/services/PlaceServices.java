package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.Place;

import java.util.List;

/**
 * Place Methods For Envirify App.
 *
 * @author Error 418
 */
public interface PlaceServices {

    /**
     * add a place of a user
     *
     * @param place the place
     * @param email the owner
     * @throws EnvirifyException When that place already exists or not exist the user.
     */
    void addPlace(Place place, String email) throws EnvirifyException;

    /**
     * Gets the places based on a city or department name.
     *
     * @param search The term to search
     * @return A List With The Places that are in the search term city or department.
     * @throws EnvirifyException When The Search Fails or does not have any result.
     */
    List<Place> getPlacesByCityOrDepartment(String search) throws EnvirifyException;
}