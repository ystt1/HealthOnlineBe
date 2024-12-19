package com.example.health_online_backend.repository;

import com.example.health_online_backend.models.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findDoctorsByNameContainingIgnoreCase(String name);
    Doctor findDoctorByEmailAndAndPassword(String email, String password);
    Doctor findDoctorByEmail(String email);
}
