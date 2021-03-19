package edu.eci.ieti.envirify.model;

import edu.eci.ieti.envirify.controllers.dtos.BookDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private Date initialDate;
    private Date finalDate;
    private String userId;
    private String placeId;


    public Book() {
    }

    public Book(Date initialDate, Date finalDate, String userId, String placeId) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.userId = userId;
        this.placeId = placeId;
    }

    public Book(BookDTO bookDTO) {
        this.initialDate = bookDTO.getInitialDate();
        this.finalDate = bookDTO.getFinalDate();
        this.userId = bookDTO.getUserId();
        this.placeId = bookDTO.getPlaceId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public boolean hasConflict(Date dateOfInit, Date dateOfEnd) {
        return (dateOfInit.before(initialDate) && dateOfEnd.after(finalDate)) || isNotAValidDate(dateOfInit) || isNotAValidDate(dateOfEnd);
    }

    private boolean isNotAValidDate(Date date) {
        return (date.after(initialDate) && date.before(finalDate)) || date.equals(initialDate) || date.equals(finalDate);
    }
}
