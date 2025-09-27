package com.medtrack.service;

import com.medtrack.dto.AppointmentRequestDTO;
import com.medtrack.dto.AppointmentResponseDTO;
import com.medtrack.exception.ResourceNotFoundException;
import com.medtrack.model.Appointment;
import com.medtrack.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAppointment() {
        AppointmentRequestDTO request = new AppointmentRequestDTO();
        request.setDoctorName("Dr. Rao");
        request.setPatientName("Kishore");
        request.setDate(LocalDate.of(2025, 9, 28));
        request.setTime(LocalTime.of(10, 30));

        Appointment saved = new Appointment();
        saved.setId(1L);
        saved.setDoctorName("Dr. Rao");
        saved.setPatientName("Kishore");
        saved.setAppointmentTime(LocalDateTime.of(2025, 9, 28, 10, 30));

        when(appointmentRepository.save(any())).thenReturn(saved);

        AppointmentResponseDTO response = appointmentService.createAppointment(request);

        assertEquals(1L, response.getId());
        assertEquals("Dr. Rao", response.getDoctorName());
        assertEquals("Kishore", response.getPatientName());
    }

    @Test
    void testGetAppointmentById_Found() {
        Appointment appointment = new Appointment();
        appointment.setId(2L);
        appointment.setDoctorName("Dr. Rao");
        appointment.setPatientName("Kishore");
        appointment.setAppointmentTime(LocalDateTime.of(2025, 9, 28, 10, 30));

        when(appointmentRepository.findById(2L)).thenReturn(Optional.of(appointment));

        AppointmentResponseDTO response = appointmentService.getAppointmentById(2L);

        assertEquals(2L, response.getId());
        assertEquals("Kishore", response.getPatientName());
    }

    @Test
    void testGetAppointmentById_NotFound() {
        when(appointmentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            appointmentService.getAppointmentById(99L);
        });
    }

    @Test
    void testUpdateAppointment() {
        AppointmentRequestDTO request = new AppointmentRequestDTO();
        request.setDoctorName("Dr. Rao");
        request.setPatientName("Kishore");
        request.setDate(LocalDate.of(2025, 9, 28));
        request.setTime(LocalTime.of(10, 30));

        Appointment existing = new Appointment();
        existing.setId(3L);
        existing.setDoctorName("Old Doc");
        existing.setPatientName("Old Patient");
        existing.setAppointmentTime(LocalDateTime.of(2025, 9, 27, 9, 0));

        when(appointmentRepository.findById(3L)).thenReturn(Optional.of(existing));
        when(appointmentRepository.save(any())).thenReturn(existing);

        AppointmentResponseDTO response = appointmentService.updateAppointment(3L, request);

        assertEquals("Dr. Rao", response.getDoctorName());
        assertEquals("Kishore", response.getPatientName());
    }

    @Test
    void testGetAppointmentsByDateRange() {
        LocalDate from = LocalDate.of(2025, 9, 1);
        LocalDate to = LocalDate.of(2025, 9, 30);
        Pageable pageable = PageRequest.of(0, 5);

        Appointment appointment = new Appointment();
        appointment.setId(4L);
        appointment.setDoctorName("Dr. Rao");
        appointment.setPatientName("Kishore");
        appointment.setAppointmentTime(LocalDateTime.of(2025, 9, 15, 11, 0));

        Page<Appointment> page = new PageImpl<>(List.of(appointment));

        when(appointmentRepository.findByAppointmentTimeBetween(any(), any(), eq(pageable))).thenReturn(page);

        Page<AppointmentResponseDTO> result = appointmentService.getAppointmentsByDateRange(from, to, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Kishore", result.getContent().get(0).getPatientName());
    }
}