package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.User;

public interface UserServices {
    void addUser(User user) throws EnvirifyException;
}
