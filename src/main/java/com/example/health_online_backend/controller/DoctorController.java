package com.example.health_online_backend.controller;


import com.example.health_online_backend.models.Doctor;
import com.example.health_online_backend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/login")
    public ResponseEntity<Doctor> login(@RequestParam String email,@RequestParam String password) {
        Doctor doctor=doctorService.login(email,password);
        if(doctor==null || doctor.getStatus()==1 ) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    };
}
