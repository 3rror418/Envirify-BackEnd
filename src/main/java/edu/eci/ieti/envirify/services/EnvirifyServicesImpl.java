package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.model.Greeting;
import edu.eci.ieti.envirify.persistence.EnvirifyPersistence;
import edu.eci.ieti.envirify.persistence.EnvirifyPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EnvirifyServicesImpl implements EnvirifyServices {

    @Autowired
    private EnvirifyPersistence persistence;

    @Override
    public Greeting getGreeting(String name) throws EnvirifyException {
        Greeting greeting = null;
        try {
            greeting = persistence.getGreeting(name);
        } catch (EnvirifyPersistenceException e) {
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.NOT_FOUND);
        }
        return greeting;
    }
}
