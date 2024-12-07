package com.example.health_online_backend.services;

import com.example.health_online_backend.models.Review;
import com.example.health_online_backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public boolean addReview(Review review) {
        try {
            reviewRepository.save(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Review> getAllReviews(String doctorId) {
        return reviewRepository.getReviewsByIdDoctor(doctorId);
    }

    public Review getReviewOfDoctorWithPatientId(String doctorId,String patientId) {
        return reviewRepository.getReviewsByIdDoctorAndIdPatient(doctorId,patientId);
    }

    public boolean saveReview(Review review) {
        try {
            reviewRepository.save(review);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
