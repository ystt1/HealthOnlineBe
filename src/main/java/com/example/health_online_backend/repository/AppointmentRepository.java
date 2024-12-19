package com.example.health_online_backend.repository;

import com.example.health_online_backend.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    @Query("{'dayBooking': {$gte: ?0, $lte: ?1}}")
    List<Appointment> findAppointmentsByDayBookingBetweenAndDoctorId(LocalDate startDate, LocalDate endDate,String doctorId);

    Appointment findAppointmentByDoctorIdAndDayBookingAndHourBooking(String doctorId, LocalDate dayBooking, int hourBooking);

    List<Appointment> findAppointmentsByDoctorIdAndDayBooking(String doctorId, LocalDate dayBooking);
    List<Appointment> findAppointmentsByDoctorIdAndDayBookingGreaterThan(String doctorId, LocalDate dayBooking);
    List<Appointment> findAppointmentsByDoctorIdAndDayBookingLessThan(String doctorId, LocalDate dayBooking);
    List<Appointment> findAppointmentsByUserId(String userId);

    List<Appointment> findAppointmentsByDoctorId(String doctorId);
}
