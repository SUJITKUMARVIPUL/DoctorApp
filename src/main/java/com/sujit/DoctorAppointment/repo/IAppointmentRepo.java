package com.sujit.DoctorAppointment.repo;

import com.sujit.DoctorAppointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment,Integer> {
}
