package edu.eci.ieti.envirify.controllers;

import edu.eci.ieti.envirify.controllers.dtos.CreatePlaceDTO;
import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.services.PlaceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for User Functions Of The Envirify App.
 *
 * @author Error 418
 */

@RestController
@RequestMapping(value = "api/v1/places")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class PlaceController {

    @Autowired
    private PlaceServices services;

    /**
     * Gets the places based on a city or department name.
     *
     * @param search The term to search, if it is not provided we calculate based on the Colombian Most Populates Place Bogota.
     * @return A Response Entity With The Places Or With The Error Message.
     * @throws EnvirifyException When The Consult Fails.
     */
    @GetMapping()
    public ResponseEntity<Object> getPlacesByCityOrDepartment(@RequestParam(defaultValue = "Bogota") String search) throws EnvirifyException {
        List<Place> places = services.getPlacesByCityOrDepartment(search);
        List<CreatePlaceDTO> placeDTOS = new ArrayList<>();
        places.forEach(place -> placeDTOS.add(new CreatePlaceDTO(place)));
        return new ResponseEntity<>(placeDTOS, HttpStatus.OK);
    }

    /**
     * Adds a new place of a user to the Envirify App
     *
     * @param placeDTO new place
     * @param email    email of user
     * @return A Response Entity With The Request Status Code.
     * @throws EnvirifyException When the place cannot be created.
     */
    @PostMapping()
    public ResponseEntity<Object> addNewPlace(@RequestBody CreatePlaceDTO placeDTO, @RequestHeader("X-Email") String email) throws EnvirifyException {
        Place newPlace = new Place(placeDTO);
        services.addPlace(newPlace, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
