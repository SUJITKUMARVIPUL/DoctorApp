package com.sujit.DoctorAppointment.repo;

import com.sujit.DoctorAppointment.model.Doctor;
import com.sujit.DoctorAppointment.model.Qualification;
import com.sujit.DoctorAppointment.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctorRepo extends JpaRepository<Doctor,Integer> {
    List<Doctor> findByDocQualificationOrDocSpecialization(Qualification qual, Specialization spec);

}
