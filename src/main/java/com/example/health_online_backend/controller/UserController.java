package com.example.health_online_backend.controller;

import com.example.health_online_backend.DTO.AuthResponse;
import com.example.health_online_backend.DTO.ChangePasswordRequest;
import com.example.health_online_backend.DTO.DoctorResponse;

import com.example.health_online_backend.DTO.UpdateProfileRequest;
import com.example.health_online_backend.models.Appointment;
import com.example.health_online_backend.models.Doctor;
import com.example.health_online_backend.models.Review;
import com.example.health_online_backend.models.User;
import com.example.health_online_backend.services.AppointmentService;
import com.example.health_online_backend.services.DoctorService;
import com.example.health_online_backend.services.ReviewService;
import com.example.health_online_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        if (UserService.isValidEmail(user.getEmail())) {
            if (UserService.isValidPassword(user.getPassword())) {
                if (user.getFullName().length() > 4) {
                    AuthResponse signUpResponse = userService.addUser(user);
                    if (signUpResponse.isSuccess()) {
                        return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
                    }
                    return new ResponseEntity<>(signUpResponse, HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new AuthResponse(false, "Tên phải có ít nhất 5 kí tự"), HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(new AuthResponse(false, "Mật khẩu có ít nhất 5 kí tự và ít nhất 1 chữ cái"), HttpStatus.UNAUTHORIZED);
            }

        } else {
            return new ResponseEntity<>(new AuthResponse(false, "Email không hợp lệ"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam String email, @RequestParam String password) {
        AuthResponse loginResponse = userService.login(email, password);

        if (loginResponse.isSuccess()) {
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/getListDoctor")
    public ResponseEntity<List<DoctorResponse>> getListDoctor() {
        try {
            List<DoctorResponse> doctorResponses = doctorService.getAllDoctor();
            if (doctorResponses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for (DoctorResponse doctor : doctorResponses) {
                List<Review> reviews = new ArrayList<>();
                reviews = reviewService.getAllReviews(doctor.getId());
                if (reviews.isEmpty()) {
                    doctor.setNumberReview(0);
                    doctor.setAverageStar(0);
                } else {
                    doctor.setNumberReview(reviews.size());
                    long totalStar = reviews.stream().mapToInt(Review::getStar).sum();
                    doctor.setAverageStar((float) totalStar / reviews.size());
                }
            }
            return new ResponseEntity<>(doctorResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-list-doctor")
    public ResponseEntity<List<DoctorResponse>> findListDoctor(@RequestParam String name) {
        try {
            List<DoctorResponse> doctorResponses = doctorService.findDoctor(name);
            if (doctorResponses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for (DoctorResponse doctor : doctorResponses) {
                List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctor.getId());
                doctor.setNumberBooking(appointments.size());
                List<Review> reviews = new ArrayList<>();
                reviews = reviewService.getAllReviews(doctor.getId());
                if (reviews.isEmpty()) {
                    doctor.setNumberReview(0);
                    doctor.setAverageStar(0);
                } else {
                    doctor.setNumberReview(reviews.size());
                    long totalStar = reviews.stream().mapToInt(Review::getStar).sum();
                    doctor.setAverageStar((float) totalStar / reviews.size());
                }
            }
            return new ResponseEntity<>(doctorResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        if (UserService.isValidPassword(request.getNewPassword())) {
            boolean isUpdated = userService.changePassword(request.getId(), request.getOldPassword(), request.getNewPassword());
            if (isUpdated) {
                return ResponseEntity.ok("Password updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials or password change failed.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu có ít nhất 5 kí tự ít nhất 1 chữ");
    }


    @PostMapping("/update-profile")
    public ResponseEntity<String> editProfile(@RequestBody UpdateProfileRequest request) {
        boolean isUpdated = userService.updateProfile(request.getId(), request.getFullName(), request.getPhoneNumber());
        if (isUpdated) {
            return ResponseEntity.ok("Profile update successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong.");
        }
    }
}
