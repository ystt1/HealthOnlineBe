package com.example.health_online_backend.services;

import com.example.health_online_backend.models.Medicine;
import com.example.health_online_backend.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository medicineRepository;


    public boolean addMedicine(Medicine medicine) {
       try{
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
}
