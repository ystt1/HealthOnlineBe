package com.example.health_online_backend.services;

import com.example.health_online_backend.models.PrescriptionMedicine;
import com.example.health_online_backend.repository.PrescriptionMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionMedicineService {

    @Autowired
    private PrescriptionMedicineRepository prescriptionMedicineRepository;

    public PrescriptionMedicine addPrescriptionMedicine(PrescriptionMedicine pms) {
        return prescriptionMedicineRepository.save(pms);
    }

    public List<PrescriptionMedicine> getPrescriptionMedicineByPrescriptionId(String idPrescription) {
        try {
            return prescriptionMedicineRepository.findPrescriptionMedicinesByIdPrescription(idPrescription);
        } catch (Exception e) {
            return null;
        }
    }


}
