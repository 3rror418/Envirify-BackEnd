package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.model.Greeting;
import edu.eci.ieti.envirify.persistence.repositories.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvirifyPersistenceImpl implements EnvirifyPersistence {

    @Autowired
    private BasicRepository repository;

    @Override
    public Greeting getGreeting(String name) throws EnvirifyPersistenceException {
        Greeting greeting = repository.findByContent(name);
        if (greeting == null) {
            throw new EnvirifyPersistenceException("There is no greeting with the content :" + name);
        }

        /*
        El save retorna la entidad con el id auto generado
        System.out.println(repository.save(new Greeting("prueba 3")));
         */

        System.out.println("Greetings found with findAll():");
        System.out.println("-------------------------------");
        for (Greeting greet : repository.findAll()) {
            System.out.println(greet);
        }
        System.out.println("-------------------------------");
        System.out.println("Greeting found with findByContent()");
        System.out.println(repository.findByContent("prueba"));

        return greeting;
    }
}
