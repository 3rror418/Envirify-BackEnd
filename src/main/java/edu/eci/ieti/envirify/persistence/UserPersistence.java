package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.User;

public interface UserPersistence {
    void addUser(User user) throws EnvirifyPersistenceException;
}
