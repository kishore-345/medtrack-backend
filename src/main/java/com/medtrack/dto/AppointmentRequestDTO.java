package com.medtrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequestDTO {

    @NotBlank(message = "Doctor name is required")
    private String doctorName;

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotNull(message = "Appointment date is required")
    private LocalDate date;

    @NotNull(message = "Appointment time is required")
    private LocalTime time;

    // Getters and Setters

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}