package com.sujit.DoctorAppointment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    private String patientName;

    @Email
    private String patientEmail;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$!%])[A-Za-z\\d@#$!%]{8,}$\n")
    private String patientPassword;

    @Enumerated(value = EnumType.STRING)
    private Gender patientGender;

    @Enumerated(value = EnumType.STRING)
    private BloopGroup patientBloodGroup;


    @Size(min = 10,max = 10)
    @Pattern(regexp = "\\d+")
    private String patientContact;


    private LocalDateTime patientDateOFBirth;

    @OneToMany(mappedBy = "patient")
    List<Appointment> appointments;
}
