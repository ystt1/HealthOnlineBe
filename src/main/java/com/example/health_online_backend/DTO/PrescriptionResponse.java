package com.example.health_online_backend.DTO;

import com.example.health_online_backend.models.PrescriptionMedicine;

import java.util.List;

public class PrescriptionResponse {
    String note;
    String diagnosis;
    List<MedicineInPrescription> medicineInPrescription;


    public PrescriptionResponse() {
    }

    public PrescriptionResponse(String note, String diagnosis, List<MedicineInPrescription> medicineInPrescription) {
        this.note = note;
        this.diagnosis = diagnosis;
        this.medicineInPrescription = medicineInPrescription;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<MedicineInPrescription> getMedicineInPrescription() {
        return medicineInPrescription;
    }

    public void setMedicineInPrescription(List<MedicineInPrescription> medicineInPrescription) {
        this.medicineInPrescription = medicineInPrescription;
    }
}
