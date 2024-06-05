package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.UserRepo;
import com.mathew.bank.Mathewbank.DTO.UserApplicationDTO;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UnRegUserService {

    @Autowired
    private UserRepo userRepo;

    // sanity check also hold for phone number digit check
    private boolean applicationObjectCheck(UserApplicationDTO userApplicationDTO){
        return userApplicationDTO.getFullName().isEmpty()
        || (userApplicationDTO.getPhoneNumber().isEmpty() || !userApplicationDTO.getPhoneNumber().matches("[0-9]+"))
        || userApplicationDTO.getDateOfBirth() == null || userApplicationDTO.getAge() <= 0
        || userApplicationDTO.getEmail() == null;
    }

    public Boolean applyForABankAccount(int branchId, UserApplicationDTO userApplicationDTO, HttpServletResponse response){

        //sanity check
        if(userApplicationDTO == null || branchId <= 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        if(this.applicationObjectCheck(userApplicationDTO)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        //try and persist
        try{
            //DTO to DAO for persistence
            this.userRepo.applyForBankAccount(branchId, new UserApplication(
                    userApplicationDTO.getFullName(),
                    userApplicationDTO.getPhoneNumber(),
                    userApplicationDTO.getDateOfBirth(),
                    userApplicationDTO.getAge(),
                    userApplicationDTO.getEmail(),
                    LocalDateTime.now(),
                    //Zero so that it will indicate it is an unresolved application
                    false,
                    null
            ));
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }

        return true;
    }

}
