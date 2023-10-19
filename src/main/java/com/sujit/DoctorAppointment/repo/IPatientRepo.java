package com.sujit.DoctorAppointment.repo;

import com.sujit.DoctorAppointment.model.BloopGroup;
import com.sujit.DoctorAppointment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPatientRepo extends JpaRepository<Patient,Integer> {
    Patient findFirstByPatientEmail(String patientEmail);

    List<Patient> findByPatientBloodGroup(BloopGroup bloodGroup);

}
