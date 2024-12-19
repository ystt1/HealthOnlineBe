package com.example.health_online_backend.DTO;

import java.time.LocalDate;

public class    ReviewResponse {
    private String comment;
    private String userName;
    private int star;
    private LocalDate date;

    public ReviewResponse() {
    }

    public ReviewResponse(String comment, String userName, int star, LocalDate date) {
        this.comment = comment;
        this.userName = userName;
        this.star = star;
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
