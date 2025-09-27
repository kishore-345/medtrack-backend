
package com.medtrack.dto;

import com.medtrack.model.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentFilterDTO {
    public List<Appointment> filterByDate(LocalDateTime start, LocalDateTime end) {
        
        return List.of();
    }
}