package edu.eci.ieti.envirify.controllers;

import edu.eci.ieti.envirify.controllers.dtos.CreateUserDTO;
import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class UserController {

    @Autowired
    private UserServices services;

    @PostMapping()
    public ResponseEntity<?> addNewUser(@RequestBody CreateUserDTO userDTO) throws EnvirifyException {
        User newUser = new User(userDTO);
        services.addUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
