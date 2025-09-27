package com.medtrack.service;

import com.medtrack.dto.AppointmentRequestDTO;
import com.medtrack.dto.AppointmentResponseDTO;
import com.medtrack.exception.ResourceNotFoundException;
import com.medtrack.model.Appointment;
import com.medtrack.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO request) {
        logger.info("Creating appointment for patient: {}", request.getPatientName());

        Appointment appointment = new Appointment();
        appointment.setDoctorName(request.getDoctorName());
        appointment.setPatientName(request.getPatientName());
        appointment.setAppointmentTime(LocalDateTime.of(
                request.getDate().getYear(),
                request.getDate().getMonthValue(),
                request.getDate().getDayOfMonth(),
                request.getTime().getHour(),
                request.getTime().getMinute()
        ));
        appointment.setReason("Created via service");

        Appointment saved = appointmentRepository.save(appointment);
        logger.debug("Appointment created with ID: {}", saved.getId());

        AppointmentResponseDTO response = new AppointmentResponseDTO();
        response.setId(saved.getId());
        response.setDoctorName(saved.getDoctorName());
        response.setPatientName(saved.getPatientName());
        response.setDate(request.getDate());
        response.setTime(request.getTime());

        return response;
    }

    public AppointmentResponseDTO getAppointmentById(Long id) {
        logger.info("Fetching appointment with ID: {}", id);

        Optional<Appointment> optional = appointmentRepository.findById(id);
        if (optional.isEmpty()) {
            logger.warn("Appointment not found with ID: {}", id);
            throw new ResourceNotFoundException("Appointment not found with ID: " + id);
        }

        Appointment appointment = optional.get();
        logger.debug("Fetched appointment for doctor: {}", appointment.getDoctorName());

        AppointmentResponseDTO response = new AppointmentResponseDTO();
        response.setId(appointment.getId());
        response.setDoctorName(appointment.getDoctorName());
        response.setPatientName(appointment.getPatientName());
        response.setDate(appointment.getAppointmentTime().toLocalDate());
        response.setTime(appointment.getAppointmentTime().toLocalTime());

        return response;
    }

    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO request) {
        logger.info("Updating appointment with ID: {}", id);

        Optional<Appointment> optional = appointmentRepository.findById(id);
        if (optional.isEmpty()) {
            logger.warn("Appointment not found for update with ID: {}", id);
            throw new ResourceNotFoundException("Appointment not found with ID: " + id);
        }

        Appointment appointment = optional.get();
        appointment.setDoctorName(request.getDoctorName());
        appointment.setPatientName(request.getPatientName());
        appointment.setAppointmentTime(LocalDateTime.of(
                request.getDate().getYear(),
                request.getDate().getMonthValue(),
                request.getDate().getDayOfMonth(),
                request.getTime().getHour(),
                request.getTime().getMinute()
        ));
        appointment.setReason("Updated via service");

        Appointment updated = appointmentRepository.save(appointment);
        logger.debug("Updated appointment for patient: {}", updated.getPatientName());

        AppointmentResponseDTO response = new AppointmentResponseDTO();
        response.setId(updated.getId());
        response.setDoctorName(updated.getDoctorName());
        response.setPatientName(updated.getPatientName());
        response.setDate(request.getDate());
        response.setTime(request.getTime());

        return response;
    }

    public Page<AppointmentResponseDTO> getAppointmentsByDateRange(LocalDate from, LocalDate to, Pageable pageable) {
        logger.info("Fetching appointments from '{}' to '{}'", from, to);

        LocalDateTime start = from.atStartOfDay();
        LocalDateTime end = to.atTime(23, 59, 59);

        Page<Appointment> page = appointmentRepository.findByAppointmentTimeBetween(start, end, pageable);

        return page.map(appointment -> {
            AppointmentResponseDTO dto = new AppointmentResponseDTO();
            dto.setId(appointment.getId());
            dto.setDoctorName(appointment.getDoctorName());
            dto.setPatientName(appointment.getPatientName());
            dto.setDate(appointment.getAppointmentTime().toLocalDate());
            dto.setTime(appointment.getAppointmentTime().toLocalTime());
            return dto;
        });
    }
}