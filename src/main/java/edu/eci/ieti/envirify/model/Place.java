package edu.eci.ieti.envirify.model;

import edu.eci.ieti.envirify.controllers.dtos.CreatePlaceDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "places")
public class Place {

    @Id
    private String id;
    private String name;
    private String department;
    private String city;
    private String direction;
    private int capacity;
    private int habitations;
    private int bathrooms;
    private String description;
    private List<String> guidebooks;
    private List<String> ratings;
    private List<String> bookings;


    public Place() {
    }

    public Place(CreatePlaceDTO placeDTO){
        this.name= placeDTO.getName();
        this.department= placeDTO.getDepartment();
        this.city= placeDTO.getCity();
        this.direction= placeDTO.getDirection();
        this.capacity= placeDTO.getCapacity();
        this.habitations= placeDTO.getHabitations();
        this.bathrooms= placeDTO.getBathrooms();
        guidebooks= new ArrayList<>();
        ratings = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHabitations() {
        return habitations;
    }

    public void setHabitations(int habitations) {
        this.habitations = habitations;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGuidebooks() {
        return guidebooks;
    }

    public void setGuidebooks(List<String> guidebooks) {
        this.guidebooks = guidebooks;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }

    public List<String> getBookings() {
        return bookings;
    }

    public void setBookings(List<String> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(name, place.name) && Objects.equals(city, place.city) && Objects.equals(direction, place.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, direction);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", city='" + city + '\'' +
                ", capacity='" + capacity + '\'' +
                ", habitations='" + habitations + '\'' +
                ", books=" + bookings +
                '}';
    }
}
