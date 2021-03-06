package edu.eci.ieti.envirify.persistence.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.persistence.UserPersistence;
import edu.eci.ieti.envirify.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class That Implements The User Persistence Methods For Envirify App.
 *
 * @author Error 418
 */
@Service
public class UserPersistenceImpl implements UserPersistence {

    @Autowired
    private UserRepository repository;

    /**
     * Adds a New User On The DB.
     *
     * @param user The User That it is going to be added.
     * @throws EnvirifyPersistenceException When that user already exists.
     */
    @Override
    public void addUser(User user) throws EnvirifyPersistenceException {
        User oldUser = repository.findByEmail(user.getEmail());
        if (oldUser != null) {
            throw new EnvirifyPersistenceException("There is already a user with the " + user.getEmail() + " email address");
        }
        repository.save(user);
    }
}
