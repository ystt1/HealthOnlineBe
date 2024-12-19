package com.example.health_online_backend.controller;

import com.example.health_online_backend.models.Medicine;
import com.example.health_online_backend.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("get-medical")
    public ResponseEntity<List<Medicine>> getMedical(@RequestParam String name) {
        try {
            List<Medicine> medicines = medicineService.getMedicineByName(name);
            return new ResponseEntity<>(medicines, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
