package com.example.health_online_backend.DTO;

public class MedicineInPrescription {
    String name;
    int quantity;
    String unit;
    String dosage;

    public MedicineInPrescription() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public MedicineInPrescription(String name, int quantity, String unit, String dosage) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.dosage = dosage;
    }
}
