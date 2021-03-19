package edu.eci.ieti.envirify.services;

import edu.eci.ieti.envirify.exceptions.EnvirifyException;
import edu.eci.ieti.envirify.model.Book;

/**
 * Books Methods For Envirify App.
 *
 * @author Error 418
 */
public interface BookServices {

    /**
     * Adds a New Booking On The App.
     *
     * @param book  The Book Information.
     * @param email The Email Of The User.
     * @throws EnvirifyException When The User or The Place Not Exist.
     */
    void addNewBooking(Book book, String email) throws EnvirifyException;
}
