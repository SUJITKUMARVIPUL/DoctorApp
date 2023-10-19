package com.sujit.DoctorAppointment.service;

import com.sujit.DoctorAppointment.model.PatientAuthenticationToken;
import com.sujit.DoctorAppointment.repo.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PAuthenticationService {

    @Autowired
    IAuthenticationRepo authenticationRepo;

    public void createToken(PatientAuthenticationToken token){
        authenticationRepo.save(token);
    }

    public void deleteToken(String tokenValue){
        PatientAuthenticationToken token = authenticationRepo.findFirstByTokenValue(tokenValue);
        authenticationRepo.delete(token);
    }

    public boolean Authenticate(String Email,String TokenValue){
        PatientAuthenticationToken token = authenticationRepo.findFirstByTokenValue(TokenValue);
        if(token!=null){
            return token.getPatient().getPatientEmail().equals(Email);
        }else{
            return false;
        }
    }
}
