package edu.eci.ieti.envirify.services.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.persistence.PlacePersistence;
import edu.eci.ieti.envirify.persistence.UserPersistence;
import edu.eci.ieti.envirify.persistence.impl.PlacePersistenceImpl;
import edu.eci.ieti.envirify.services.PlaceServices;
import edu.eci.ieti.envirify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class That Implements The User Methods For Envirify App.
 *
 * @author Error 418
 */
@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserPersistence persistence;

    /**
     * Adds a New User On The App.
     *
     * @param user The User That it is going to be added.
     * @throws EnvirifyException When that user cannot be added.
     */
    @Override
    public void addUser(User user) throws EnvirifyException {
        try {
            persistence.addUser(user);
        } catch (EnvirifyPersistenceException e) {
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.CONFLICT);
        }
    }
    
    /**
     * Update a user on the app.
     *
     * @param user The User That it is going to be updated.
     * @throws EnvirifyException When that user cannot be updated.
     */
    @Override
    public void updateUser(User user, String email) throws EnvirifyException {
        try {
            persistence.updateUser(user, email);
        } catch (EnvirifyPersistenceException e) {
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.CONFLICT);
        }
    }

    /**
     * Returns the Information Of A User With a Email.
     *
     * @param email The email to search the user.
     * @return The User Information.
     * @throws EnvirifyException When that users do not exist.
     */
    @Override
    public User getUserByEmail(String email) throws EnvirifyException {
        User user;
        try {
            user = persistence.getUserByEmail(email);
        } catch (EnvirifyPersistenceException e) {
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.NOT_FOUND);
        }
        return user;
    }


}
