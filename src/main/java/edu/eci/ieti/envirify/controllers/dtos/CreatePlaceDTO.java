package edu.eci.ieti.envirify.controllers.dtos;

import java.io.Serializable;

/**
 * Data Transfer Object For Create Place Request.
 *
 * @author Error 418
 */
public class CreatePlaceDTO implements Serializable {

    private String name;
    private String department;
    private String city;
    private String direction;
    private int capacity;
    private int habitations;
    private int bathrooms;

    /**
     * Basic constructor
     */
    public CreatePlaceDTO() {
    }

    /**
     * CreatePlaceDTO constructor
     * @param name the name
     * @param department the department
     * @param city the city
     * @param direction the direction
     * @param capacity the capacity
     * @param habitations the habitations number
     * @param bathrooms the bathrooms number
     */
    public CreatePlaceDTO(String name, String department, String city, String direction, int capacity, int habitations, int bathrooms) {
        this.name = name;
        this.department = department;
        this.city = city;
        this.direction = direction;
        this.capacity = capacity;
        this.habitations = habitations;
        this.bathrooms = bathrooms;
    }

    /**
     * get the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * set the name
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the department
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * set the department
     * @param department the department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * get the city
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * set the city
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * get the direction
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * set the direction
     * @param direction the direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * get the capacity
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * set the capacity
     * @param capacity the capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * get the habitations
     * @return the habitations
     */
    public int getHabitations() {
        return habitations;
    }


    /**
     * set the habitations
     * @param habitations the habitations
     */
    public void setHabitations(int habitations) {
        this.habitations = habitations;
    }

    /**
     * get the bathrooms
     * @return the bathrooms
     */
    public int getBathrooms() {
        return bathrooms;
    }

    /**
     * set the bathrooms
     * @param bathrooms the bathrooms
     */
    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }
}
