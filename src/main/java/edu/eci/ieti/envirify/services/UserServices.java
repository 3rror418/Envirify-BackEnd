package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.User;

/**
 * User Methods For Envirify App.
 *
 * @author Error 418
 */
public interface UserServices {

    /**
     * Adds a New User On The App.
     *
     * @param user The User That it is going to be added.
     * @throws EnvirifyException When that user cannot be added.
     */
    void addUser(User user) throws EnvirifyException;

    /**
     * Returns the Information Of A User With a Email.
     *
     * @param email The email to search the user.
     * @return The User Information.
     * @throws EnvirifyException When that users do not exist.
     */
    User getUserByEmail(String email) throws EnvirifyException;
}
