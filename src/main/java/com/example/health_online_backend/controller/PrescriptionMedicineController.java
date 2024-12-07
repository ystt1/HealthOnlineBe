package com.example.health_online_backend.controller;

import com.example.health_online_backend.models.PrescriptionMedicine;
import com.example.health_online_backend.services.PrescriptionMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/prescription-medicine")
public class PrescriptionMedicineController {
    @Autowired
    PrescriptionMedicineService prescriptionMedicineService;


}
