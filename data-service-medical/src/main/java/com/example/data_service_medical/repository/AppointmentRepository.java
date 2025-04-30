package com.example.data_service_medical.repository;

import com.example.data_service_medical.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a WHERE " +
            "(COALESCE(:patientName, '') = '' OR a.patientName LIKE %:patientName%) AND " +
            "(:doctorId IS NULL OR a.doctor.id = :doctorId) AND " +
            "(COALESCE(:status, '') = '' OR a.status = :status)")
    List<Appointment> findWithFilters(String patientName, Integer doctorId, String status);

    @Query("SELECT d.name, d.specialization, COUNT(a.id) as appointmentCount " +
            "FROM Appointment a JOIN a.doctor d " +
            "GROUP BY d.id, d.name, d.specialization " +
            "ORDER BY appointmentCount DESC")
    List<Object[]> findTopDoctors();

    @Query("SELECT DATE(a.appointmentTime), COUNT(a.id) " +
            "FROM Appointment a " +
            "GROUP BY DATE(a.appointmentTime) " +
            "ORDER BY DATE(a.appointmentTime)")
    List<Object[]> countAppointmentsByDay();

    @Query("SELECT a.patientName, COUNT(a.id) as appointmentCount " +
            "FROM Appointment a " +
            "GROUP BY a.patientName " +
            "ORDER BY appointmentCount DESC")
    List<Object[]> findFrequentPatients();
}