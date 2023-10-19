package com.sujit.DoctorAppointment.repo;

import com.sujit.DoctorAppointment.model.PatientAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<PatientAuthenticationToken,Integer> {
    PatientAuthenticationToken findFirstByTokenValue(String tokenValue);

}
