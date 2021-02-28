package edu.eci.ieti.envirify.controllers;

import edu.eci.ieti.envirify.services.EnvirifyException;
import edu.eci.ieti.envirify.services.EnvirifyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvirifyController {

    @Autowired
    private EnvirifyServices services;

    @GetMapping("/greeting")
    public ResponseEntity<?> getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) throws EnvirifyException {
        return new ResponseEntity<>(services.getGreeting(name), HttpStatus.ACCEPTED);
    }
}
