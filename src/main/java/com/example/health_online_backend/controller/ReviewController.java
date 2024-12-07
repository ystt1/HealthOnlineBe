package com.example.health_online_backend.controller;

import com.example.health_online_backend.DTO.ReviewResponse;
import com.example.health_online_backend.models.Review;
import com.example.health_online_backend.models.User;
import com.example.health_online_backend.services.ReviewService;
import com.example.health_online_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @PostMapping("add-review")
    public ResponseEntity<String> addReview(@RequestBody Review review) {
        Review reviewGet = reviewService.getReviewOfDoctorWithPatientId(review.getIdDoctor(), review.getIdPatient());
        if (reviewGet == null) {
            review.setCreatedDate(LocalDate.now());
            if (reviewService.addReview(review)) {
                return ResponseEntity.ok("Review added");
            } else
                return ResponseEntity.internalServerError().body("Add review failed.");
        } else {
            reviewGet.setCreatedDate(LocalDate.now());
            reviewGet.setComment(review.getComment());
            reviewGet.setStar(review.getStar());
            if (reviewService.addReview(reviewGet)) {
                return ResponseEntity.ok("Review changed");
            } else
                return ResponseEntity.internalServerError().body("Changed Unsuccessful.");
        }
    }

    @GetMapping("get-review")
    public ResponseEntity<List<ReviewResponse>> getAllReviewOfDoctor(@RequestParam String doctorId) {
        List<Review> reviews = reviewService.getAllReviews(doctorId);
        if (reviews == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            List<ReviewResponse> responses = new ArrayList<>();
            for (Review review : reviews) {
                User user = userService.getPatientById(review.getIdPatient());
                ReviewResponse reviewResponse = new ReviewResponse();
                reviewResponse.setUserName(user.getFullName());
                reviewResponse.setComment(review.getComment());
                reviewResponse.setStar(review.getStar());
                reviewResponse.setDate(review.getCreatedDate());
                responses.add(reviewResponse);
            }
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
    }

    @GetMapping("get-a-review")
    public ResponseEntity<Review> getReviewOfDoctorWithPatientId(@RequestParam String doctorId, @RequestParam String patientId) {
        Review review = reviewService.getReviewOfDoctorWithPatientId(doctorId, patientId);
        if(review!=null)
        {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
