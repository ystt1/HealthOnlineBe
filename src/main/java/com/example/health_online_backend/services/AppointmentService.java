package com.example.health_online_backend.services;

import com.example.health_online_backend.DTO.TimeSlot;
import com.example.health_online_backend.models.Appointment;
import com.example.health_online_backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    public List<TimeSlot> getNextSevenDaysAppointments(String doctorId) {
        LocalDate today = LocalDate.now();
        //LocalDate sevenDaysLater = today.plusDays(7);
        List<TimeSlot> timeSlots = new ArrayList<>();
        ;
        for (int i = 1; i < 8; i++) {
            List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctorIdAndDayBooking(doctorId, today.plusDays(i));

            List<Integer> slots = new ArrayList<>();
            for (Appointment appointment : appointments) {
                slots.add(appointment.getHourBooking());
            }
            timeSlots.add(new TimeSlot(i, slots));

        }
        return timeSlots;
    }

    public List<Appointment> getListAppointmentTodayOfDoctor(String doctorId,int type) {
        LocalDate today = LocalDate.now();
        if(type==0) {
            return appointmentRepository.findAppointmentsByDoctorIdAndDayBooking(doctorId, today);
        }
        if(type==1) {
             return appointmentRepository.findAppointmentsByDoctorIdAndDayBookingGreaterThan(doctorId, today);
        }
        return appointmentRepository.findAppointmentsByDoctorIdAndDayBookingLessThan(doctorId, today);

    }


    public List<Integer> getSlotsXDayLater(String doctorId, int i) {
        LocalDate today = LocalDate.now();
        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctorIdAndDayBooking(doctorId, today.plusDays(i));
        List<Integer> slots = new ArrayList<>();
        for (Appointment appointment : appointments) {
            slots.add(appointment.getHourBooking());
        }
        return slots;
    }

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public boolean isTimeSlotTaken(String doctorId, LocalDate date, int slot) {
        return appointmentRepository.findAppointmentByDoctorIdAndDayBookingAndHourBooking(doctorId, date, slot) != null;
    }


    public Appointment getAppointment(String id) {
        try {
            return appointmentRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Appointment> getAppointmentsByPatientId(String id) {
        return appointmentRepository.findAppointmentsByUserId(id);
    }

    public List<Appointment> getAppointmentsByDoctorId(String id) {
        return appointmentRepository.findAppointmentsByDoctorId(id);
    }

    public void setAppointmentDone(Appointment appointment) {
        appointment.setStatus(1);
        appointmentRepository.save(appointment);
    }
}
