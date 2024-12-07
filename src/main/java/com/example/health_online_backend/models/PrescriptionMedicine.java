package com.example.health_online_backend.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PrescriptionMedicine")
public class PrescriptionMedicine {

    @Id
    private String id;

    private String idPrescription;
    private String idMedicine;
    private String dosage;
    private int quantity;

    public PrescriptionMedicine() {
    }

    public PrescriptionMedicine(String id, String idPrescription, String idMedicine, String dosage, int quantity) {
        this.id = id;
        this.idPrescription = idPrescription;
        this.idMedicine = idMedicine;
        this.dosage = dosage;
        this.quantity = quantity;
    }

    public PrescriptionMedicine(String id, String idPrescription, String idMedicine, int quantity) {
        this.id = id;
        this.idPrescription = idPrescription;
        this.idMedicine = idMedicine;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPrescription() {
        return idPrescription;
    }

    public void setIdPrescription(String idPrescription) {
        this.idPrescription = idPrescription;
    }

    public String getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(String idMedicine) {
        this.idMedicine = idMedicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
