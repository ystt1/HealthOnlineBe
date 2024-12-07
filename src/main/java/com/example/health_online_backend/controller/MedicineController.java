package com.example.health_online_backend.controller;

import com.example.health_online_backend.models.Medicine;
import com.example.health_online_backend.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/medicine")
public class MedicineController {
    @Autowired
    MedicineService medicineService;

    @PostMapping()
    public ResponseEntity<String> addMedicine(@RequestBody Medicine medicine) {
        try {
            medicineService.addMedicine(medicine);
            return ResponseEntity.ok("Medicine added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Medicine not added");
        }
    }
}
