package edu.eci.ieti.envirify.controllers.dtos;

import edu.eci.ieti.envirify.model.Rating;

import java.io.Serializable;

public class RatingDTO implements Serializable {

    private String id;
    private String comment;
    private Integer qualification;

    /**
     * Basic constructor
     */
    public RatingDTO() {
    }

    public RatingDTO(String comment, Integer qualification) {
        this.comment = comment;
        this.qualification = qualification;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }
}
