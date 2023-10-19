package com.sujit.DoctorAppointment.service;

import com.sujit.DoctorAppointment.model.BloopGroup;
import com.sujit.DoctorAppointment.model.Patient;
import com.sujit.DoctorAppointment.model.PatientAuthenticationToken;
import com.sujit.DoctorAppointment.repo.IAuthenticationRepo;
import com.sujit.DoctorAppointment.repo.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatientRepo patientRepo;

    @Autowired
    PAuthenticationService authenticationService;

    public String patientSignUp(Patient patient){
        Patient existP = patientRepo.findFirstByPatientEmail(patient.getPatientEmail());
        if(existP!=null){
            return "email already used";
        }
        try {
            String encryptedPass = PasswordEncryptor.encrypt(patient.getPatientPassword());
            patient.setPatientPassword(encryptedPass);
            patientRepo.save(patient);
            return "Patient added successfully";
        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue";
        }
    }

    public String patientSignIn(String Email,String Password){
        Patient patient = patientRepo.findFirstByPatientEmail(Email);
        try {
            if(PasswordEncryptor.encrypt(Password).equals(patient.getPatientPassword())){
                PatientAuthenticationToken token = new PatientAuthenticationToken(patient);
                authenticationService.createToken(token);
                return "Sign In Successful";
            }else{
                return "Wrong Password";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue";
        }
    }

    public String patientSignOut(String Email,String TokenValue){
        if(authenticationService.Authenticate(Email,TokenValue)){
            authenticationService.deleteToken(TokenValue);
            return "Sign Out successfully";
        }else{
            return "Un Authorised access";
        }
    }

    public List<Patient> getAllPatients() {

        return patientRepo.findAll();
    }

    public List<Patient> getAllPatientsByBloodGroup(BloopGroup bloodGroup) {

        List<Patient> patients = patientRepo.findByPatientBloodGroup(bloodGroup);

        for(Patient patient: patients)
        {
            patient.setAppointments(null);
        }

        return patients;
    }


}
