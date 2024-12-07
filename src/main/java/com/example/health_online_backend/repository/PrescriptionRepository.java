package com.example.health_online_backend.repository;

import com.example.health_online_backend.models.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
    Prescription findPrescriptionByIdAppointment(String id);
}
