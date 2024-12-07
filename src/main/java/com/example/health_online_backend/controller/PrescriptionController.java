package com.example.health_online_backend.controller;


import com.example.health_online_backend.DTO.MedicineInPrescription;
import com.example.health_online_backend.DTO.PrescriptionPost;
import com.example.health_online_backend.DTO.PrescriptionResponse;
import com.example.health_online_backend.models.Appointment;
import com.example.health_online_backend.models.Medicine;
import com.example.health_online_backend.models.Prescription;
import com.example.health_online_backend.models.PrescriptionMedicine;
import com.example.health_online_backend.services.AppointmentService;
import com.example.health_online_backend.services.MedicineService;
import com.example.health_online_backend.services.PrescriptionMedicineService;
import com.example.health_online_backend.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionMedicineService prescriptionMedicineService;
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicineService medicineService;

    @PostMapping("add-prescription")
    ResponseEntity<String> addPrescription(@RequestBody PrescriptionPost prescriptionPost) {
        try {

            if (prescriptionPost == null || prescriptionPost.getPrescriptionMedicineList() == null) {
                return new ResponseEntity<>("Invalid request data", HttpStatus.BAD_REQUEST);
            }

            Appointment appointment = appointmentService.getAppointment(prescriptionPost.getIdAppointment());
            if (appointment==null) {
                return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
            }
            appointmentService.setAppointmentDone(appointment);
            Prescription prescription = new Prescription();
            prescription.setIdAppointment(prescriptionPost.getIdAppointment());
            prescription.setDiagnosis(prescriptionPost.getDiagnosis());
            prescription.setNote(prescriptionPost.getNote());

            Prescription preResponse = prescriptionService.addPrescription(prescription);
            if (preResponse != null) {
                for (PrescriptionMedicine pm : prescriptionPost.getPrescriptionMedicineList()
                ) {
                    pm.setIdPrescription(preResponse.getId());
                    prescriptionMedicineService.addPrescriptionMedicine(pm);
                }
                return new ResponseEntity<>("Add success", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Add Prescription false", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get-prescription")
    ResponseEntity<PrescriptionResponse> getPrescription(@RequestParam("idAppointment") String idAppointment) {
        try {
            Prescription prescription = prescriptionService.getPrescriptionByAppointmentId(idAppointment);

            PrescriptionResponse prescriptionResponse = new PrescriptionResponse();
            prescriptionResponse.setDiagnosis(prescription.getDiagnosis());
            prescriptionResponse.setNote(prescription.getNote());
            List<PrescriptionMedicine> prescriptionMedicines = prescriptionMedicineService.getPrescriptionMedicineByPrescriptionId(prescription.getId());
            List<MedicineInPrescription> medicineInPrescriptions = new ArrayList<MedicineInPrescription>();
            for (PrescriptionMedicine element : prescriptionMedicines) {
                MedicineInPrescription medicineInPrescription = new MedicineInPrescription();
                medicineInPrescription.setDosage(element.getDosage());
                medicineInPrescription.setQuantity(element.getQuantity());
                Medicine medicine= medicineService.getMedicineById(element.getIdMedicine());
                medicineInPrescription.setName(medicine.getName());
                medicineInPrescription.setUnit(medicine.getUnit());
                medicineInPrescriptions.add(medicineInPrescription);
            }
            prescriptionResponse.setMedicineInPrescription(medicineInPrescriptions);
            return new ResponseEntity<>(prescriptionResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
