package com.sujit.DoctorAppointment.repo;

import com.sujit.DoctorAppointment.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Integer> {
}
