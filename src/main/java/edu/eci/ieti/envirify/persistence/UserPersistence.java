package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.User;

/**
 * User Persistence Methods For Envirify App.
 *
 * @author Error 418
 */
public interface UserPersistence {
    /**
     * Adds a New User On The DB.
     *
     * @param user The User That it is going to be added.
     * @throws EnvirifyPersistenceException When that user already exists.
     */
    void addUser(User user) throws EnvirifyPersistenceException;
}
