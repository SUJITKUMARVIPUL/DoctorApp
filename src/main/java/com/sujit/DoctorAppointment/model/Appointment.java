package com.sujit.DoctorAppointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;
    private String  appointmentDesc;

    LocalDateTime appCreationTime;
    LocalDateTime appScheduleTime;


    @ManyToOne()
    @JoinColumn(name = "fk_patient_id")
    Patient patient;

    @ManyToOne()
    @JoinColumn(name = "fk_doc_id")
    Doctor doctor;
}
