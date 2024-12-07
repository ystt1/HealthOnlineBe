package com.example.health_online_backend.controller;

import com.example.health_online_backend.DTO.AppointmentHistoryOfUser;
import com.example.health_online_backend.DTO.TimeSlot;
import com.example.health_online_backend.models.Appointment;
import com.example.health_online_backend.models.Doctor;
import com.example.health_online_backend.services.AppointmentService;
import com.example.health_online_backend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;


    @GetMapping("/getwithdoctor")
    public ResponseEntity<List<TimeSlot>> getAllAppointmentsInNextSevenDayOfDoctor(@RequestParam String doctorId) {
        try {
            List<TimeSlot> timeSlots = appointmentService.getNextSevenDaysAppointments(doctorId);
            return new ResponseEntity<>(timeSlots, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTimeSlot")
    public ResponseEntity<List<Integer>> GetListSlotInXDayLater(@RequestParam String doctorId, @RequestParam int xDayLater) {
        try {
            List<Integer> slots = appointmentService.getSlotsXDayLater(doctorId, xDayLater);
            return new ResponseEntity<>(slots, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("addAppointment")
    public ResponseEntity<String> addAppointment(@RequestBody Appointment appointment) {
        try {
            if (appointmentService.isTimeSlotTaken(appointment.getDoctorId(), appointment.getDayBooking(), appointment.getHourBooking())) {
                return new ResponseEntity<>("Appointment is already taken", HttpStatus.CONFLICT);
            }
            appointmentService.addAppointment(appointment);
            return new ResponseEntity<>("Appointment added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("get-history")
    ResponseEntity<List<AppointmentHistoryOfUser>> getHistory(@RequestParam String userId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(userId);

            List<AppointmentHistoryOfUser> appointmentHistory = new ArrayList<>();

            for (Appointment appointment : appointments) {
                AppointmentHistoryOfUser appointmentHistoryOfUser = new AppointmentHistoryOfUser();
                appointmentHistoryOfUser.setDoctorId(appointment.getDoctorId());
                appointmentHistoryOfUser.setId(appointment.getId());
                appointmentHistoryOfUser.setDate(appointment.getDayBooking());
                appointmentHistoryOfUser.setSlot(appointment.getHourBooking());
                appointmentHistoryOfUser.setStatus(appointment.getStatus());
                appointmentHistoryOfUser.setAge(appointment.getAge());
                appointmentHistoryOfUser.setName(appointment.getVictimName());
                Doctor doctor = doctorService.getDoctorById(appointment.getDoctorId());
                appointmentHistoryOfUser.setDoctorName(doctor.getName());
                appointmentHistoryOfUser.setSpecialization(doctor.getSpecialized());
                appointmentHistory.add(appointmentHistoryOfUser);
            }
            return new ResponseEntity<>(appointmentHistory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
