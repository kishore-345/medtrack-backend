
package com.medtrack.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private LocalDateTime appointmentTime;
    private String reason;

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}