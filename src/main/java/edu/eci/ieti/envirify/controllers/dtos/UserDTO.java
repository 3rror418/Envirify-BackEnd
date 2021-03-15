package edu.eci.ieti.envirify.controllers.dtos;

import edu.eci.ieti.envirify.model.Place;
import edu.eci.ieti.envirify.model.User;

import java.util.List;

/**
 * Data Transfer Object For Get User Request.
 *
 * @author Error 418
 */
public class UserDTO {

    private final String id;
    private final String email;
    private final String name;
    private final String phoneNumber;
    private final String gender;
    private final List<String> books;
    private final List<String> places;
    private final List<String> chats;

    /**
     * Constructor For UserDTO.
     *
     * @param user A User Object.
     */
    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.gender = user.getGender();
        this.books = user.getBooks();
        this.places = user.getPlaces();
        this.chats = user.getChats();
    }

    /**
     * Returns the Id of the User.
     *
     * @return A string that represents the Id of the User.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the Email of the User.
     *
     * @return A string that represents the Email of the User.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the Name of the User.
     *
     * @return A string that represents the Name of the User.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Phone Number of the User.
     *
     * @return A string that represents the Phone Number of the User.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the Gender of the User.
     *
     * @return A string that represents the Gender of the User.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns the Books of the User.
     *
     * @return A collection of strings that represents the Books of the User.
     */
    public List<String> getBooks() {
        return books;
    }

    /**
     * Returns the Places of the User.
     *
     * @return A collection of strings that represents the Places of the User.
     */
    public List<String> getPlaces() {
        return places;
    }

    /**
     * Returns the Chats of the User.
     *
     * @return A collection of strings that represents the Chats of the User.
     */
    public List<String> getChats() {
        return chats;
    }
}
