package edu.eci.ieti.envirify.persistence;

import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Book;

/**
 * Books Persistence Methods For Envirify App.
 *
 * @author Error 418
 */
public interface BookPersistence {

    /**
     * Adds a New Booking On The DB.
     *
     * @param book  The Book Information.
     * @param email The User Email
     * @throws EnvirifyPersistenceException When Something Fails.
     */
    void addNewBooking(Book book, String email) throws EnvirifyPersistenceException;
}
