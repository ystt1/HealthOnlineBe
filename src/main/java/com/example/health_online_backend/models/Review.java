package com.example.health_online_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("Review")
public class Review {
    @Id
    private String id;

    private String idDoctor;
    private String idPatient;
    private String comment;
    private int star;
    private LocalDate createdDate;

    public Review() {
    }

    public Review(String idDoctor, String idPatient, String comment, int star) {
        this.idDoctor = idDoctor;
        this.idPatient = idPatient;
        this.comment = comment;
        this.star = star;
    }

    public Review(String comment, int star) {
        this.comment = comment;
        this.star = star;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
