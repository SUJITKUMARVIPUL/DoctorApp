package com.sujit.DoctorAppointment.controller;

import com.sujit.DoctorAppointment.model.BloopGroup;
import com.sujit.DoctorAppointment.model.Doctor;
import com.sujit.DoctorAppointment.model.Patient;
import com.sujit.DoctorAppointment.service.DoctorService;
import com.sujit.DoctorAppointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Validated
public class AdminController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @GetMapping("patients")
    public List<Patient> getAllPatients()
    {
        return patientService.getAllPatients();
    }

    @PostMapping("doctor")
    public String addDoctor(@RequestBody Doctor newDoctor)
    {
        return doctorService.addDoctor(newDoctor);
    }

    @GetMapping("patients/bloodGroup/{bloodGroup}")
    public List<Patient> getAllPatientsByBloodGroup(@PathVariable BloopGroup bloodGroup)
    {
        return patientService.getAllPatientsByBloodGroup(bloodGroup);
    }
}
