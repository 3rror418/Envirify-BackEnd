package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.model.Greeting;

public interface EnvirifyServices {
    Greeting getGreeting(String name) throws EnvirifyException;
}
