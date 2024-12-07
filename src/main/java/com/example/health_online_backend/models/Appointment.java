package com.example.health_online_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;

@Document("Appointments")
public class Appointment {
    @Id
    private String id;

    private String userId;
    private String doctorId;
    private LocalDate dayBooking;
    private int hourBooking;
    private String victimName;
    private String description;
    private int age;
    private int status;
    public Appointment() {
    }



    public Appointment(String userId, String doctorId, LocalDate dayBooking, int hourBooking, String victimName, String description, int age) {
        this.userId = userId;
        this.doctorId = doctorId;
        this.dayBooking = dayBooking;
        this.hourBooking = hourBooking;
        this.victimName = victimName;
        this.description = description;
        this.age = age;
        this.status = 0;
    }

    public Appointment(String id, String userId, String doctorId, LocalDate dayBooking, int hourBooking, String victimName, String description, int age) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.dayBooking = dayBooking;
        this.hourBooking = hourBooking;
        this.victimName = victimName;
        this.description = description;
        this.age = age;
    }

    public String getVictimName() {
        return victimName;
    }

    public void setVictimName(String victimName) {
        this.victimName = victimName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDayBooking() {
        return dayBooking;
    }

    public void setDayBooking(LocalDate dayBooking) {
        this.dayBooking = dayBooking;
    }

    public int getHourBooking() {
        return hourBooking;
    }

    public void setHourBooking(int hourBooking) {
        this.hourBooking = hourBooking;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
