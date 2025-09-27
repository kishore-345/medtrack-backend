package com.medtrack.controller;

import com.medtrack.model.Medicine;
import com.medtrack.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "*")
public class MedicineController {

    @Autowired
    private MedicineService service;

    @GetMapping("/expiring")
    public List<Medicine> getExpiringSoon() {
        return service.getExpiringSoon();
    }

    @PostMapping("/add")
    public Medicine addMedicine(@RequestBody Medicine medicine) {
        return service.saveMedicine(medicine);
    }

    @GetMapping("/all")
    public List<Medicine> getAll() {
        return service.getAllMedicines();
    }
}