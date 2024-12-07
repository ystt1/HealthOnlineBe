package com.example.health_online_backend.repository;

import com.example.health_online_backend.models.PrescriptionMedicine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrescriptionMedicineRepository extends MongoRepository<PrescriptionMedicine, String> {
    List<PrescriptionMedicine> findPrescriptionMedicinesByIdPrescription(String id);
}
