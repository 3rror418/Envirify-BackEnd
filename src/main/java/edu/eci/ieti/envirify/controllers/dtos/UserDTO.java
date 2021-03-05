package edu.eci.ieti.envirify.controllers.dtos;

import edu.eci.ieti.envirify.model.User;

import java.util.List;

public class UserDTO {

    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String gender;
    private List<String> books;
    private List<String> places;
    private List<String> chats;

    public UserDTO() {
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }

    public List<String> getChats() {
        return chats;
    }

    public void setChats(List<String> chats) {
        this.chats = chats;
    }
}
