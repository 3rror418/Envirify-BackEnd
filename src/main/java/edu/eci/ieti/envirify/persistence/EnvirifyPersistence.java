package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.model.Greeting;

public interface EnvirifyPersistence {
    Greeting getGreeting(String name) throws EnvirifyPersistenceException;
}
