package com.example.health_online_backend.services;

import com.example.health_online_backend.DTO.DoctorResponse;
import com.example.health_online_backend.models.Doctor;
import com.example.health_online_backend.models.Medicine;
import com.example.health_online_backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Doctor login(String email, String password) {
        try {
            return doctorRepository.findDoctorByEmailAndAndPassword(email, password);
        }
        catch (Exception e) {
            return null;
        }
    }

    public Doctor getDoctorById(String id) {
        try {
            return doctorRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }
    public Doctor getDoctorByEmail(String id) {
        try {
            return doctorRepository.findDoctorByEmail(id);
        }
        catch (Exception e) {
            return null;
        }
    }
    public List<DoctorResponse> getAllDoctor() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorResponse> doctorResponses = new ArrayList<>();
        for (Doctor doc : doctors) {
            if(doc.getStatus()==0)
            {
                doctorResponses.add(new DoctorResponse(doc));
            }
        }
        return doctorResponses;
    }

    public List<DoctorResponse> findDoctor(String name) {
        List<Doctor> doctors = doctorRepository.findDoctorsByNameContainingIgnoreCase(name);
        List<DoctorResponse> doctorResponses = new ArrayList<>();
        for (Doctor doc : doctors) {
            if(doc.getStatus()==0)
            {
                doctorResponses.add(new DoctorResponse(doc));
            }

        }
        return doctorResponses;
    }
    public List<Doctor> findListDoctor(String name) {
        return doctorRepository.findDoctorsByNameContainingIgnoreCase(name);
    }

    public void updateDoctor(Doctor oldDoctor,Doctor newDoctor) {
        oldDoctor.setPassword(newDoctor.getPassword());
        oldDoctor.setName(newDoctor.getName());
        oldDoctor.setDescription(newDoctor.getDescription());
        oldDoctor.setSpecialized(newDoctor.getSpecialized());
        oldDoctor.setStatus(newDoctor.getStatus());
        doctorRepository.save(oldDoctor);
    }

    public void addDoctor(Doctor doctor) {
        doctor.setStatus(0);
        doctorRepository.save(doctor);
    }

    public boolean delete(Doctor doctor) {
        try{
            doctor.setStatus(1);
            doctorRepository.save(doctor);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public boolean reverse(Doctor doctor) {
        try{
            doctor.setStatus(0);
            doctorRepository.save(doctor);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }
}
