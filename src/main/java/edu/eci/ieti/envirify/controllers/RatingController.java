package edu.eci.ieti.envirify.controllers;

import edu.eci.ieti.envirify.controllers.dtos.RatingDTO;
import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.Rating;
import edu.eci.ieti.envirify.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/ratings")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class RatingController {

    @Autowired
    private RatingServices services;

    @PostMapping()
    public ResponseEntity<Object> postRating(@RequestParam String placeId, @RequestBody RatingDTO ratingDTO) throws EnvirifyException {
        Rating rating = new Rating(ratingDTO);
        services.addRating(rating,placeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
