package com.example.health_online_backend.services;

import com.example.health_online_backend.DTO.DoctorResponse;
import com.example.health_online_backend.models.Doctor;
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

    public Doctor getDoctorById(String id) {
        try {
            return doctorRepository.findById(id).get();
        }
        catch (Exception e) {
            return null;
        }
    }
    public List<DoctorResponse> getAllDoctor() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorResponse> doctorResponses = new ArrayList<>();
        for (Doctor doc : doctors) {
            doctorResponses.add(new DoctorResponse(doc));
        }
        return doctorResponses;
    }

    public List<DoctorResponse> findDoctor(String name) {
        List<Doctor> doctors = doctorRepository.findDoctorsByNameContainingIgnoreCase(name);
        List<DoctorResponse> doctorResponses = new ArrayList<>();
        for (Doctor doc : doctors) {
            doctorResponses.add(new DoctorResponse(doc));
        }
        return doctorResponses;
    }

}
