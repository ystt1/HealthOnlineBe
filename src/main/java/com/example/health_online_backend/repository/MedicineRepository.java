package com.example.health_online_backend.repository;

import com.example.health_online_backend.models.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface MedicineRepository extends MongoRepository<Medicine, String> {
}
