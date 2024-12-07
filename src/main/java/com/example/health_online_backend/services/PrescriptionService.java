package com.example.health_online_backend.services;

import com.example.health_online_backend.models.Prescription;
import com.example.health_online_backend.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription addPrescription(Prescription prescription) {
        try {
            return prescriptionRepository.save(prescription);
        } catch (Exception e) {
            return null;
        }
    }

    public Prescription getPrescriptionByAppointmentId(String id) {
        try {
            return prescriptionRepository.findPrescriptionByIdAppointment(id);
        } catch (Exception e) {
            return null;
        }
    }
}
