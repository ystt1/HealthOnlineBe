package com.example.health_online_backend.DTO;

import com.example.health_online_backend.models.Doctor;

public class DoctorResponse {
    private String id;
    private String name;
    private String description;
    private String specialized;
    private float averageStar;
    private long numberReview;
    private long numberBooking;

    public long getNumberBooking() {
        return numberBooking;
    }

    public void setNumberBooking(long numberBooking) {
        this.numberBooking = numberBooking;
    }

    public DoctorResponse() {
    }

    public DoctorResponse(String name, String description, String specialized, String id) {
        this.name = name;
        this.description = description;
        this.specialized = specialized;
        this.id = id;
    }

    public DoctorResponse(String name, String description, String specialized, String id,int numberBooking) {
        this.name = name;
        this.description = description;
        this.specialized = specialized;
        this.id = id;
        this.numberBooking = numberBooking;
    }

    public DoctorResponse(String id, String name, String description, String specialized, float averageStar, long numberReview) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.specialized = specialized;
        this.averageStar = averageStar;
        this.numberReview = numberReview;
    }

    public DoctorResponse(String id, String name, String description, String specialized, float averageStar, long numberReview, int numberBooking) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.specialized = specialized;
        this.averageStar = averageStar;
        this.numberReview = numberReview;
        this.numberBooking = numberBooking;
    }

    public float getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(float averageStar) {
        this.averageStar = averageStar;
    }

    public long getNumberReview() {
        return numberReview;
    }

    public void setNumberReview(long numberReview) {
        this.numberReview = numberReview;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }

    public DoctorResponse(Doctor doctor) {
        this.name = doctor.getName();
        this.description = doctor.getDescription();
        this.specialized = doctor.getSpecialized();
        this.id = doctor.getId();
    }
}
