package com.medtrack.controller;

import com.medtrack.dto.PatientDTO;
import com.medtrack.model.Patient;
import com.medtrack.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody PatientDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        return ResponseEntity.ok(patientRepository.save(patient));
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}