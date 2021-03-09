package edu.eci.ieti.envirify.controllers;

import edu.eci.ieti.envirify.controllers.dtos.CreatePlaceDTO;
import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.persistence.repositories.PlaceRepository;
import edu.eci.ieti.envirify.security.jwt.JwtUtils;
import edu.eci.ieti.envirify.services.PlaceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/places")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class PlaceController {

    @Autowired
    private PlaceServices services;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping()
    public ResponseEntity<Object> addNewPlace(@RequestBody CreatePlaceDTO placeDTO , @RequestHeader("X-Email")String email) throws EnvirifyException {

        Place newPlace = new Place(placeDTO);
        services.addPlace(newPlace,email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
