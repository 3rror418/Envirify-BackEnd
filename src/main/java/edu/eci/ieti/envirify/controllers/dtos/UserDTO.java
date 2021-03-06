package edu.eci.ieti.envirify.controllers.dtos;

import edu.eci.ieti.envirify.model.User;

import java.util.List;

/**
 * Data Transfer Object For Get User Request.
 *
 * @author Error 418
 */
public class UserDTO {

    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String gender;
    private List<String> books;
    private List<String> places;
    private List<String> chats;

    /**
     * Basic Constructor For UserDTO.
     */
    public UserDTO() {
        //Left Empty On Purpose For The Controllers Conversion.
    }

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
     * Sets a New Id For The User.
     *
     * @param id The New Id For The User.
     */
    public void setId(String id) {
        this.id = id;
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
     * Sets a New Email For The User.
     *
     * @param email The New Email For The User.
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Sets a New Name For The User.
     *
     * @param name The New Name For The User.
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets a New Phone Number For The User.
     *
     * @param phoneNumber The New Phone Number For The User.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
     * Sets a New Gender For The User.
     *
     * @param gender The New Gender For The User.
     */
    public void setGender(String gender) {
        this.gender = gender;
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
     * Sets New Books For The User.
     *
     * @param books The New Books For The User.
     */
    public void setBooks(List<String> books) {
        this.books = books;
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
     * Sets New Places For The User.
     *
     * @param places The New Places For The User.
     */
    public void setPlaces(List<String> places) {
        this.places = places;
    }

    /**
     * Returns the Chats of the User.
     *
     * @return A collection of strings that represents the Chats of the User.
     */
    public List<String> getChats() {
        return chats;
    }

    /**
     * Sets New Chats For The User.
     *
     * @param chats The New Chats For The User.
     */
    public void setChats(List<String> chats) {
        this.chats = chats;
    }
}
