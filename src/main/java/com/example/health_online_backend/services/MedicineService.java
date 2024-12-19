package com.example.health_online_backend.services;

import com.example.health_online_backend.models.Medicine;
import com.example.health_online_backend.models.User;
import com.example.health_online_backend.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository medicineRepository;


    public boolean addMedicine(Medicine medicine) {
       try{
           medicine.setStatus(0);
           medicineRepository.save(medicine);
           return true;
       }
       catch(Exception e){
           return false;
       }
    }

    public Medicine getMedicineById(String id) {
        return medicineRepository.findById(id).get();
    }

    public List<Medicine> getMedicineByName(String name) {
        List<Medicine> medicines= medicineRepository.findByNameLike(name);
        List<Medicine> medicines2 = new  ArrayList<>();
        for(Medicine medicine:medicines){
            if(medicine.getStatus() != 1){
                medicines2.add(medicine);
            }
        }
        return medicines2;
    }

    public void updateMedicalAdmin(Medicine medicine, Medicine medicineNew) {

        medicine.setName(medicineNew.getName());
        medicine.setUnit(medicineNew.getUnit());
        medicine.setQuantity(medicineNew.getQuantity());
        medicineRepository.save(medicine);

    }

    public boolean delete(Medicine medicine) {
        try{
            medicine.setStatus(1);
            medicineRepository.save(medicine);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public boolean reverse(Medicine medicine) {
        try{
            medicine.setStatus(0);
            medicineRepository.save(medicine);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
}
