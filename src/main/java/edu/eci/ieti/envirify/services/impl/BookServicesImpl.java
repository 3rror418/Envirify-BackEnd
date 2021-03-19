package edu.eci.ieti.envirify.services.impl;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Book;
import edu.eci.ieti.envirify.persistence.BookPersistence;
import edu.eci.ieti.envirify.persistence.PlacePersistence;
import edu.eci.ieti.envirify.persistence.UserPersistence;
import edu.eci.ieti.envirify.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookServicesImpl implements BookServices {

    @Autowired
    private BookPersistence persistence;

    @Autowired
    private PlacePersistence placePersistence;

    @Autowired
    private UserPersistence userPersistence;

    @Override
    public void addNewBooking(Book book, String email) throws EnvirifyException {
        Date actualDate = new Date();
        if (book.getInitialDate().before(actualDate)) {
            throw new EnvirifyException("The Booking can not be made for a date prior to today's date", HttpStatus.CONFLICT);
        }
        if (book.getInitialDate().after(book.getFinalDate())) {
            throw new EnvirifyException("The End date of the booking must be after the starting date", HttpStatus.CONFLICT);
        }
        if (book.getPlaceId() == null) {
            throw new EnvirifyException("The booking has to specify the place Id", HttpStatus.BAD_REQUEST);
        }
        try {
            persistence.addNewBooking(book, email);
        } catch (EnvirifyPersistenceException e) {
            if (e.getMessage().equals(EnvirifyPersistenceException.DATE_INTERVAL_ERROR)) {
                throw new EnvirifyException(e.getMessage(), e, HttpStatus.CONFLICT);
            }
            throw new EnvirifyException(e.getMessage(), e, HttpStatus.NOT_FOUND);
        }

    }
}
