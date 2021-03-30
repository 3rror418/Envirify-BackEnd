package edu.eci.ieti.envirify.persistence.impl;

import edu.eci.ieti.envirify.controllers.dtos.BookPlaceDTO;
import edu.eci.ieti.envirify.exceptions.EnvirifyPersistenceException;
import edu.eci.ieti.envirify.model.Book;
import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.model.User;
import edu.eci.ieti.envirify.persistence.UserPersistence;
import edu.eci.ieti.envirify.persistence.repositories.BookRepository;
import edu.eci.ieti.envirify.persistence.repositories.PlaceRepository;
import edu.eci.ieti.envirify.persistence.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Class That Implements The User Persistence Methods For Envirify App.
 *
 * @author Error 418
 */
@Service
public class UserPersistenceImpl implements UserPersistence {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PlaceRepository placeRepository;
    
    @Autowired
    private BookRepository bookRepository;

    /**
     * Adds a New User On The DB.
     *
     * @param user The User That it is going to be added.
     * @throws EnvirifyPersistenceException When that user already exists.
     */
    @Override
    public void addUser(User user) throws EnvirifyPersistenceException {
        User oldUser = repository.findByEmail(user.getEmail());
        if (oldUser != null) {
            throw new EnvirifyPersistenceException("There is already a user with the " + user.getEmail() + " email address");
        }
        repository.save(user);
    }
    
    /**
     *  Update a user on the DB.
     *
     * @param user The User That it is going to be updated.
     * @throws EnvirifyPersistenceException When the email of the user to update already exists.
     */
    @Override
    public void updateUser(User user, String email) throws EnvirifyPersistenceException {
    	System.out.println(email);
    	String verifyEmail = user.getEmail();
    	User usuario = repository.findByEmail(email);
    	User verify = repository.findByEmail(user.getEmail());
    	if (verifyEmail.equals(email) || verify == null) {
			setNewUser(usuario, user);
            repository.save(user);
        }
    	else {
    		throw new EnvirifyPersistenceException("There is already a user with the " + user.getEmail() + " email address");
    	}
    }
	
	/**
     *  Set the new user data
     *
     * @param oldUser The old user that it is going to be updated.
	 * @param oldUser The new user That it is going to be updated.
     */
	public void setNewUser(User oldUser, User newUser) {
		oldUser.setName(newUser.getName());
		oldUser.setPhoneNumber(newUser.getPhoneNumber());
		oldUser.setGender(newUser.getGender());
	}

    /**
     * Returns the Information Of A User With a Email From The DB.
     *
     * @param email The email to search the user.
     * @return The User Information.
     * @throws EnvirifyPersistenceException When that users do not exist.
     */
    @Override
    public User getUserByEmail(String email) throws EnvirifyPersistenceException {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new EnvirifyPersistenceException("There is no user with the email address "+email);
        }
        return user;
    }

	private Book getBookById(String id) throws EnvirifyPersistenceException {
		Book book = null;
		Optional<Book> res = bookRepository.findById(id);
		if (res.isPresent()) {
			book = res.get();
		}
		if (book == null) {
	    	throw new EnvirifyPersistenceException("There is no booking");
		}
		return book;
	}
	
	/**
     * Returns the Bookings Of A User With a Email From The DB.
     *
     * @param email The email to search the bookings.
     * @return The User Bookings Information.
     * @throws EnvirifyPersistenceException When that user do not have bookings or that user do not exist.
     */
	 public List<BookPlaceDTO> getBookingsByEmail(String email) throws EnvirifyPersistenceException {
	        User user = repository.findByEmail(email);
	        if (user == null) {
	            throw new EnvirifyPersistenceException("There is no user with the email address "+email);
	        }
			List<String> lista = user.getBooks();
		    if (lista.isEmpty()) {
		    	throw new EnvirifyPersistenceException("There user with the email address "+email+" don't have bookings");
		    }
	        List<Book> bookings = new ArrayList<>() ;
		    for (String id:lista){
	            Book book = getBookById(id);
	            bookings.add(book);
	        }
		    List<Place> places = new ArrayList<>();
		    for (Book id:bookings){
		    	Place place = null;
		        Optional<Place> optionalPlace = placeRepository.findById(id.getPlaceId());
		        if (optionalPlace.isPresent()) {
		            place = optionalPlace.get();
		        }
		        if (place == null) {
		            throw new EnvirifyPersistenceException("There is no place with the id " + id);
		        }
	            places.add(place);
	        }

		    List<BookPlaceDTO> booksDto= new ArrayList<>();
		    for (int i=0; i<bookings.size();i++){
		    	Book book = bookings.get(i);
		    	Place place = places.get(i);
		    	BookPlaceDTO bookPlaceDTO= new BookPlaceDTO(book,place);
		    	booksDto.add(bookPlaceDTO);
			}

		    return booksDto;
		}
}
