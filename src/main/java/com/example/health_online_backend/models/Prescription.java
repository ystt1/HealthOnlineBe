package com.example.health_online_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
@Document("Prescription")
public class Prescription {
    @Id
   private String id;

   private String idAppointment;
   private LocalDate dateCreated;
   private String diagnosis;
   private String note;


    public Prescription() {
    }

    public Prescription(String id, String idAppointment, LocalDate dateCreated, String diagnosis, String note) {
        this.id = id;
        this.idAppointment = idAppointment;
        this.dateCreated = dateCreated;
        this.diagnosis = diagnosis;
        this.note = note;
    }

    public Prescription(String idAppointment, LocalDate dateCreated, String note, String diagnosis) {
        this.idAppointment = idAppointment;
        this.dateCreated = dateCreated;
        this.note = note;
        this.diagnosis = diagnosis;
    }

    public Prescription(String idAppointment, String diagnosis, String note) {
        this.dateCreated = LocalDate.now();
        this.idAppointment = idAppointment;
        this.diagnosis = diagnosis;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(String idAppointment) {
        this.idAppointment = idAppointment;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
