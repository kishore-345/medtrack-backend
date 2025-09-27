package com.medtrack.repository;

import com.medtrack.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByExpiryDateBefore(LocalDate date);
}