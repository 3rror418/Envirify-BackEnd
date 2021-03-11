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
    
    /**
     *  Update a user on the DB.
     *
     * @param user The User That it is going to be updated.
     * @throws EnvirifyPersistenceException When the email of the user to update already exists.
     */
    @Override
    public void updateUser(User user, String email) throws EnvirifyPersistenceException {
    	System.out.println(email);
    	String verifyEmail = user.getEmail();
    	User usuario = repository.findByEmail(email);
    	User verify = repository.findByEmail(user.getEmail());
    	if (verifyEmail.equals(email)) {
            if (user.getName()!= null) usuario.setName(user.getName());
            if (user.getPhoneNumber()!= null) usuario.setPhoneNumber(user.getPhoneNumber());
            if (user.getGender()!= null) usuario.setGender(user.getGender());
            repository.save(user);
    	}
    	else if (verify == null) {
    		if (user.getEmail()!= null) usuario.setEmail(user.getEmail());
            if (user.getName()!= null) usuario.setName(user.getName());
            if (user.getPhoneNumber()!= null) usuario.setPhoneNumber(user.getPhoneNumber());
            if (user.getGender()!= null) usuario.setGender(user.getGender());
            repository.save(user);
        }
    	else {
    		throw new EnvirifyPersistenceException("There is already a user with the " + user.getEmail() + " email address");
    	}
    }

    /**
     * Returns the Information Of A User With a Email From The DB.
     *
     * @param email The email to search the user.
     * @return The User Information.
     * @throws EnvirifyPersistenceException When that users do not exist.
     */
    @Override
    public User getUserByEmail(String email) throws EnvirifyPersistenceException {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new EnvirifyPersistenceException("There is no user with the email address "+email);
        }
        return user;
    }
}
