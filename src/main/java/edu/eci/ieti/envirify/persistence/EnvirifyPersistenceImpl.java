package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.model.Greeting;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class EnvirifyPersistenceImpl implements EnvirifyPersistence {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Greeting getGreeting(String name) throws EnvirifyPersistenceException {
        if (name.equals("Daniel")) {
            throw new EnvirifyPersistenceException("The name cannot be daniel");
        }
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
