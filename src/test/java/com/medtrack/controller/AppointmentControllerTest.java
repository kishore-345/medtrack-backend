package com.medtrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medtrack.dto.AppointmentRequestDTO;
import com.medtrack.dto.AppointmentResponseDTO;
import com.medtrack.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAppointment() throws Exception {
        AppointmentRequestDTO request = new AppointmentRequestDTO();
        request.setDoctorName("Dr. Rao");
        request.setPatientName("Kishore");
        request.setDate(LocalDate.of(2025, 9, 28));
        request.setTime(LocalTime.of(10, 30));

        AppointmentResponseDTO response = new AppointmentResponseDTO();
        response.setId(1L);
        response.setDoctorName("Dr. Rao");
        response.setPatientName("Kishore");
        response.setDate(request.getDate());
        response.setTime(request.getTime());

        Mockito.when(appointmentService.createAppointment(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.doctorName").value("Dr. Rao"))
                .andExpect(jsonPath("$.patientName").value("Kishore"));
    }

    @Test
    void testGetAppointmentById() throws Exception {
        AppointmentResponseDTO response = new AppointmentResponseDTO();
        response.setId(2L);
        response.setDoctorName("Dr. Rao");
        response.setPatientName("Kishore");
        response.setDate(LocalDate.of(2025, 9, 28));
        response.setTime(LocalTime.of(10, 30));

        Mockito.when(appointmentService.getAppointmentById(2L)).thenReturn(response);

        mockMvc.perform(get("/appointments/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.doctorName").value("Dr. Rao"))
                .andExpect(jsonPath("$.patientName").value("Kishore"));
    }

    @Test
    void testGetAppointmentsByDateRange() throws Exception {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setId(3L);
        dto.setDoctorName("Dr. Rao");
        dto.setPatientName("Kishore");
        dto.setDate(LocalDate.of(2025, 9, 15));
        dto.setTime(LocalTime.of(11, 0));

        Page<AppointmentResponseDTO> page = new PageImpl<>(List.of(dto));

        Mockito.when(appointmentService.getAppointmentsByDateRange(
                Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(page);

        mockMvc.perform(get("/appointments/range")
                .param("from", "2025-09-01")
                .param("to", "2025-09-30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(3))
                .andExpect(jsonPath("$.content[0].doctorName").value("Dr. Rao"))
                .andExpect(jsonPath("$.content[0].patientName").value("Kishore"));
    }
}