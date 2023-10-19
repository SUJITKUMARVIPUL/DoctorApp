package com.sujit.DoctorAppointment.controller;

import com.sujit.DoctorAppointment.model.Doctor;
import com.sujit.DoctorAppointment.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public class DoctorController {
    @Autowired
     DoctorService doctorService;

    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(@Valid @RequestParam String Email,@RequestParam String Password)
    {
        return doctorService.getAllDoctors(Email,Password);
    }


    @GetMapping("doctor/{id}")
    public Optional<Doctor> getDoctorById(@PathVariable Integer id)
    {
        return doctorService.getDoctorById(id);
    }
}
