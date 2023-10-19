package com.sujit.DoctorAppointment.service;

import com.sujit.DoctorAppointment.model.Doctor;
import com.sujit.DoctorAppointment.model.Qualification;
import com.sujit.DoctorAppointment.model.Specialization;
import com.sujit.DoctorAppointment.repo.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DoctorService {
    @Autowired
    IDoctorRepo doctorRepo;

    @Autowired
    PAuthenticationService authenticationService;


    public List<Doctor> getAllDoctors(String Email,String Password) {
        if(authenticationService.Authenticate(Email,Password)) {
            return doctorRepo.findAll();
        }
        else {
            return null;
        }
    }

    public String addDoctor(Doctor newDoctor) {

        Integer docId = newDoctor.getDocId();

        if(docId!=null && doctorRepo.existsById(docId))
        {
            return "doctor already exists!!!";
        }

        newDoctor.setAppointments(null);// linking anyway does not happen from non fk side

        doctorRepo.save(newDoctor);

        return "doctor added!!!";
    }

    public Optional<Doctor> getDoctorById(Integer id) {

        return doctorRepo.findById(id);

    }

    public List<Doctor> getDoctorsByQualificationOrSpec(String Email,String Password, Qualification qual, Specialization spec) {

        if(authenticationService.Authenticate(Email,Password)) {

            List<Doctor> doctors = doctorRepo.findByDocQualificationOrDocSpecialization(qual, spec);

            return doctors.stream().
                    map(doc -> {
                        doc.setAppointments(null);
                        return doc;
                    })
                    .collect(Collectors.toList());

        }
        else {
            return null;
        }
    }
}
