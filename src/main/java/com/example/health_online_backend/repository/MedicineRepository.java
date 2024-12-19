package com.example.health_online_backend.repository;

import com.example.health_online_backend.models.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicineRepository extends MongoRepository<Medicine, String> {
    List<Medicine> findMedicinesByNameContainingIgnoreCase(String medicineName);
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Medicine> findByNameLike(String namePattern);
}
