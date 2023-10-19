package com.sujit.DoctorAppointment.controller;

import com.sujit.DoctorAppointment.model.*;
import com.sujit.DoctorAppointment.service.AppointmentService;
import com.sujit.DoctorAppointment.service.DoctorService;
import com.sujit.DoctorAppointment.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;


    //sign up
    @PostMapping("patient/signup")
    public String patientSignUp(@Valid @RequestBody Patient patient)
    {
        return patientService.patientSignUp(patient);
    }



    //sign in
    @PostMapping("patient/signIn")
    public String patientSignIn(@RequestParam String Email,@RequestParam String Password)
    {
        return patientService.patientSignIn(Email,Password);
    }


    //sign out
    @DeleteMapping("patient/signOut")
    public String patientSignOut(@RequestParam String Email,@RequestParam String TokenValue)
    {
        return patientService.patientSignOut(Email,TokenValue);
    }


    //schedule an appointment

    @PostMapping("patient/appointment/schedule")
    public String scheduleAppointment(@RequestParam String Email,@RequestParam String Password,@RequestBody Appointment appointment)
    {
        return appointmentService.scheduleAppointment(Email,Password,appointment);
    }

    @DeleteMapping("patient/appointment/{appointmentId}/cancel")
    public String cancelAppointment(@RequestParam String Email,@RequestParam String Password, @PathVariable Integer appointmentId)
    {
        return appointmentService.cancelAppointment(Email,Password,appointmentId);
    }

    @GetMapping("doctors/qualification/{qual}/or/specialization/{spec}")
    public List<Doctor> getDoctorsByQualificationOrSpec(@RequestParam String Email, @RequestParam String Password, @PathVariable Qualification qual, @PathVariable Specialization spec)
    {
        return doctorService.getDoctorsByQualificationOrSpec(Email,Password,qual,spec);
    }
}
