package com.sujit.DoctorAppointment.service;

import com.sujit.DoctorAppointment.model.Appointment;
import com.sujit.DoctorAppointment.model.Doctor;
import com.sujit.DoctorAppointment.model.Patient;
import com.sujit.DoctorAppointment.repo.IAppointmentRepo;
import com.sujit.DoctorAppointment.repo.IDoctorRepo;
import com.sujit.DoctorAppointment.repo.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class AppointmentService {
    @Autowired
    IAppointmentRepo appointmentRepo;


    @Autowired
    IPatientRepo patientRepo;


    @Autowired
    IDoctorRepo doctorRepo;



    @Autowired
    PAuthenticationService authenticationService;

    public String scheduleAppointment(String Email,String Password, Appointment appointment) {

        if(authenticationService.Authenticate(Email,Password)) {

            Patient patient = patientRepo.findFirstByPatientEmail(Email);

            appointment.setPatient(patient);


            //find the doctor

            Integer docId = appointment.getDoctor().getDocId();

            Doctor doc = doctorRepo.findById(docId).orElseThrow();

            appointment.setDoctor(doc);


            if(doc!= null)
            {
                appointment.setAppCreationTime(LocalDateTime.now());
                appointmentRepo.save(appointment);
                return "appointment booked at time :" + appointment.getAppScheduleTime() + " with Dr. " + doc.getDocName() ;
            }
            else
            {
                return "Doctor does not exist, Could not book appointment!!!";
            }
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String cancelAppointment(String Email,String Password, Integer appointmentId) {

        if(authenticationService.Authenticate(Email,Password)) {

            Patient patient = patientRepo.findFirstByPatientEmail(Email);

            Appointment existingAppointment =  appointmentRepo.findById(appointmentId).orElseThrow();

            if(existingAppointment.getPatient().equals(patient))
            {
                appointmentRepo.deleteById(appointmentId);
                return "appointment with " + existingAppointment.getDoctor().getDocName() + " has been cancelled!!";

            }
            else
            {
                return "UnAuthorized cancel appointment!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }
    }
}
