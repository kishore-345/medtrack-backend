package com.medtrack.controller;

import com.medtrack.dto.AppointmentRequestDTO;
import com.medtrack.dto.AppointmentResponseDTO;
import com.medtrack.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO request) {
        AppointmentResponseDTO created = appointmentService.createAppointment(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDTO response = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequestDTO request) {
        AppointmentResponseDTO updated = appointmentService.updateAppointment(id, request);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/range")
    public ResponseEntity<Page<AppointmentResponseDTO>> getAppointmentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @PageableDefault(size = 5, sort = "appointmentTime", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<AppointmentResponseDTO> page = appointmentService.getAppointmentsByDateRange(from, to, pageable);
        return ResponseEntity.ok(page);
    }
}