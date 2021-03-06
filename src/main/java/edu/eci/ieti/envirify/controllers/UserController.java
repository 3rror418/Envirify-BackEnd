package edu.eci.ieti.envirify.controllers;

import edu.eci.ieti.envirify.controllers.dtos.CreateUserDTO;
import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for User Functions Of The Envirify App.
 *
 * @author Error 418
 */
@RestController
@RequestMapping(value = "api/v1/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class UserController {

    @Autowired
    private UserServices services;

    /**
     * Adds A New User On The Envirify App.
     *
     * @param userDTO Object that represents the body of the HTTP Request Of The User.
     * @return A Response Entity With The Request Status Code.
     * @throws EnvirifyException When the user cannot be created.
     */
    @PostMapping()
    public ResponseEntity<Object> addNewUser(@RequestBody CreateUserDTO userDTO) throws EnvirifyException {
        User newUser = new User(userDTO);
        services.addUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
