package edu.eci.ieti.envirify.model;

import edu.eci.ieti.envirify.controllers.dtos.CreateUserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String gender;
    private String password;
    private List<String> books;
    private List<String> places;
    private List<String> chats;

    public User() {
    }

    public User(CreateUserDTO userDTO){
        this.email = userDTO.getEmail();
        this.name = userDTO.getName();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.gender = userDTO.getGender();
        this.password = userDTO.getPassword();
        books = new ArrayList<>();
        places = new ArrayList<>();
        chats = new ArrayList<>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, phoneNumber, gender);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", books=" + books +
                ", places=" + places +
                ", chats=" + chats +
                '}';
    }
}
