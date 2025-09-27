package com.medtrack.service;

import com.medtrack.model.Medicine;
import com.medtrack.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repo;

    public List<Medicine> getExpiringSoon() {
        return repo.findByExpiryDateBefore(LocalDate.now().plusDays(30));
    }

    public Medicine saveMedicine(Medicine medicine) {
        return repo.save(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return repo.findAll();
    }
}