package edu.eci.ieti.envirify.services.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.persistence.UserPersistence;
import edu.eci.ieti.envirify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserPersistence persistence;

    @Override
    public void addUser(User user) throws EnvirifyException {
        //FALTA CIFRAR LA CLAVE, ESO LO TIENE QUE HACER EL QUE HACE EL LOGIN.
        try {
            persistence.addUser(user);
        } catch (EnvirifyPersistenceException e) {
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.CONFLICT);
        }
    }
}
