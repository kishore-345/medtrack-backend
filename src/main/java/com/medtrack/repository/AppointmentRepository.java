package com.medtrack.repository;

import com.medtrack.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Page<Appointment> findByDoctorNameContainingIgnoreCase(String doctorName, Pageable pageable);

    Page<Appointment> findByPatientNameContainingIgnoreCase(String patientName, Pageable pageable);

    Page<Appointment> findByAppointmentTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<Appointment> findByPatientNameContainingIgnoreCaseAndAppointmentTimeBetween(
        String patientName, LocalDateTime start, LocalDateTime end, Pageable pageable);
}