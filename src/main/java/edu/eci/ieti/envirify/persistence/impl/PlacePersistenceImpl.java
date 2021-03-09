package edu.eci.ieti.envirify.persistence.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.persistence.PlacePersistence;
import edu.eci.ieti.envirify.persistence.repositories.PlaceRepository;
import edu.eci.ieti.envirify.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacePersistenceImpl implements PlacePersistence {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addPlace(Place place, String email) throws EnvirifyPersistenceException {

        User user = userRepository.findByEmail(email);

        if (user==null){
            throw new EnvirifyPersistenceException("There is no user with the email address "+email);
        }

        List<Place> oldPlace = placeRepository.findByDirection(place.getDirection());
        if (!oldPlace.isEmpty()) {
            Place city = oldPlace.stream().filter(c->place.getCity().equals(c.getCity())).findFirst().orElse(null);
            if (city!=null) {
                throw new EnvirifyPersistenceException("There is already a place with the " + place.getDirection()+"-"+place.getCity() + "-address");
            }
        }

        Place newPlace = placeRepository.save(place);

        user.addPlace(newPlace.getId());

        userRepository.save(user);

    }
}
