package edu.eci.ieti.envirify.persistence.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.persistence.PlacePersistence;
import edu.eci.ieti.envirify.persistence.repositories.PlaceRepository;
import edu.eci.ieti.envirify.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class That Implements The Place Persistence Methods For Envirify App.
 *
 * @author Error 418
 */
@Service
public class PlacePersistenceImpl implements PlacePersistence {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Add a place of a user
     *
     * @param place the new place
     * @param email the email of the owner
     * @throws EnvirifyPersistenceException When the user does not exist or tha place already exists.
     */
    @Override
    public void addPlace(Place place, String email) throws EnvirifyPersistenceException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EnvirifyPersistenceException("There is no user with the email address " + email);
        }
        List<Place> oldPlace = placeRepository.findByDirection(place.getDirection());
        if (!oldPlace.isEmpty()) {
            Place city = oldPlace.stream().filter(c -> place.getCity().equals(c.getCity())).findFirst().orElse(null);
            if (city != null) {
                throw new EnvirifyPersistenceException("There is already a place with the " + place.getDirection() + "-" + place.getCity() + "-address");
            }
        }
        place.setOwner(email);
        Place newPlace = placeRepository.save(place);
        user.addPlace(newPlace.getId());
        userRepository.save(user);
    }

    /**
     * Gets the places based on a city name.
     *
     * @param city The City Name to search.
     * @return A List With The Places that are in the search term city.
     */
    @Override
    public List<Place> getPlacesByCity(String city) {
        return placeRepository.findByCity(city);
    }

    /**
     * Gets the places based on a department name.
     *
     * @param department The Department Name to search.
     * @return A List With The Places that are in the search term department.
     */
    @Override
    public List<Place> getPlacesByDepartment(String department) {
        return placeRepository.findByDepartment(department);
    }

    /**
     * Gets A Place By His ID From DB.
     *
     * @param id The Place Id.
     * @return The Place Class With That Id.
     * @throws EnvirifyPersistenceException When The Place With That Id Does Not Exist.
     */
    @Override
    public Place getPlaceById(String id) throws EnvirifyPersistenceException {
        Place place = null;
        Optional<Place> optionalPlace = placeRepository.findById(id);
        if (optionalPlace.isPresent()) {
            place = optionalPlace.get();
        }
        if (place == null) {
            throw new EnvirifyPersistenceException("There is no place with the id " + id);
        }
        return place;
    }

    @Override
    public List<Place> getPlaceByUser(String email) throws EnvirifyPersistenceException {
        User user = userRepository.findByEmail(email);
        List<String> ids = user.getPlaces();
        List<Place> places = new ArrayList<>() ;
        for (String id:ids){
            Place place = getPlaceById(id);
            places.add(place);
        }
        return places;
    }
}
