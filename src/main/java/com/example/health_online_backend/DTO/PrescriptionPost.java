package com.example.health_online_backend.DTO;

import com.example.health_online_backend.models.Prescription;
import com.example.health_online_backend.models.PrescriptionMedicine;

import java.util.List;

public class PrescriptionPost {
    private String idAppointment;
    private String diagnosis;
    private String note;

    private List<PrescriptionMedicine> prescriptionMedicineList;


    public PrescriptionPost() {
    }

    public PrescriptionPost(String idAppointment, String diagnosis, String note, List<PrescriptionMedicine> prescriptionMedicineList) {
        this.idAppointment = idAppointment;
        this.diagnosis = diagnosis;
        this.note = note;
        this.prescriptionMedicineList = prescriptionMedicineList;
    }

    public String getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(String idAppointment) {
        this.idAppointment = idAppointment;
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

    public List<PrescriptionMedicine> getPrescriptionMedicineList() {
        return prescriptionMedicineList;
    }

    public void setPrescriptionMedicineList(List<PrescriptionMedicine> prescriptionMedicineList) {
        this.prescriptionMedicineList = prescriptionMedicineList;
    }
}
