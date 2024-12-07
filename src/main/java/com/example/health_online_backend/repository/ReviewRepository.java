package com.example.health_online_backend.repository;

import com.example.health_online_backend.models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> getReviewsByIdDoctor(String userId);

    Review getReviewsByIdDoctorAndIdPatient(String doctorId, String idPatient);
}
