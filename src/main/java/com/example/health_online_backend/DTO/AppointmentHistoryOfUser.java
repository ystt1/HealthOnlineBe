package com.example.health_online_backend.DTO;

import java.time.LocalDate;

public class AppointmentHistoryOfUser {
    private String id;
    private int slot;
    private LocalDate date;
    private int status;
    private String doctorName;
    private String specialization;
    private String name;
    private int age;
    private String doctorId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public AppointmentHistoryOfUser() {
    }

    public AppointmentHistoryOfUser(String id, int slot, LocalDate date, int status, String doctorName, String specialization, String name, int age,String doctorId) {
        this.id = id;
        this.slot = slot;
        this.date = date;
        this.status = status;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.name = name;
        this.age = age;
        this.doctorId = doctorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
