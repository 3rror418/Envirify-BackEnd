package edu.eci.ieti.envirify.controllers.dtos;

import java.io.Serializable;

public class CreatePlaceDTO implements Serializable {

    private String name;
    private String department;
    private String city;
    private String direction;
    private int capacity;
    private int habitations;
    private int bathrooms;

    public CreatePlaceDTO() {
    }

    public CreatePlaceDTO(String name, String department, String city, String direction, int capacity, int habitations, int bathrooms) {
        this.name = name;
        this.department = department;
        this.city = city;
        this.direction = direction;
        this.capacity = capacity;
        this.habitations = habitations;
        this.bathrooms = bathrooms;
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
}
