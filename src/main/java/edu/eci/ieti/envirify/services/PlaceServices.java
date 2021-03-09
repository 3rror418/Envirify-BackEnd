package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.Place;


public interface PlaceServices {

    void addPlace(Place place, String email) throws EnvirifyException;
}
