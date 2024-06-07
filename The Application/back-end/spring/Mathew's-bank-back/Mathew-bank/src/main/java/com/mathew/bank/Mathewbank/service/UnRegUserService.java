package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.UserRepo;
import com.mathew.bank.Mathewbank.DTO.BranchDTO;
import com.mathew.bank.Mathewbank.DTO.UserApplicationDTO;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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

    //checks for sanity and throws an exception in case of a run time error
    //appropriate HTTP resp sent back
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
                    false,
                    null
            ));
        }
        catch (Exception e){
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }

        return true;
    }


    // uppercases the first letter of the word + sanity check
    private String transformText(String text, HttpServletResponse response){
        //if user enters an empty field
        if(text.isEmpty()){
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            throw new RuntimeException("Value is empty");
        }
        //lower case
        text = text.toLowerCase();
        //first letter uppercase + the rest of te word excluding the first letter
        return text.substring(0,1).toUpperCase()+text.substring(1);
    }

    public List<BranchDTO> branchDTOList(String country, String state, HttpServletResponse response){

        // clean input
        country = transformText(country, response);
        state = transformText(state, response);

        //transferable data format to front end
        List<BranchDTO> branchDTOS = new LinkedList<>();

        //tries to get branch info from db
        try{
            List<Branch> branches = this.userRepo.getBranchesByCountryAndName(country, state);

            //entity data is converted into DTO
            branches.forEach(branch ->
                    branchDTOS.add(new BranchDTO(
                            branch.getId(),
                            branch.getBranchName(),
                            branch.getState(),
                            branch.getCountry(),
                            branch.isOpen(),
                            0
                    )));

            response.setStatus(HttpServletResponse.SC_OK);

        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        System.out.println(country+" "+state);
        return branchDTOS;
    }

    public UserApplicationDTO getUserApplication(String phoneNumber, String email, HttpServletResponse response){

        //check if phone number is provided PRIORITY
        if(phoneNumber != null){
            // if phone number is empty
            if(phoneNumber.isEmpty()) {
                //try calling the method again to try an email  SECONDARY
                return getUserApplication(null,email,response);
            }
            //check if phone number has digits
            if(!phoneNumber.matches("[0-9]+")){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
            response.setStatus(HttpServletResponse.SC_OK);

            UserApplication userApplication;
            try{
                userApplication = this.userRepo.getUserApplicationDetailsByPhoneNumber(phoneNumber);
            }catch (Exception e){
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                System.out.println(e);
                //try calling the method again this time check with email  SECONDARY
                return getUserApplication(null,email,response);
            }

            return new UserApplicationDTO(
                    userApplication.getApplication_number(),
                    userApplication.getFullName(),
                    userApplication.getPhoneNumber(),
                    userApplication.getDateOfBirth(),
                    userApplication.getAge(),
                    userApplication.getEmail(),
                    userApplication.getAppliedOn(),
                    userApplication.isStatus(),
                    userApplication.isRejected()
            );
        }

        //if phone is not provided
        if(email != null){
            if(email.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
            response.setStatus(HttpServletResponse.SC_OK);
            UserApplication userApplication;
            try{
                userApplication = this.userRepo.getUserApplicationDetailsByEmail(email);
            }catch (Exception e){
                System.out.println(e);
                response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                return null;
            }

            return new UserApplicationDTO(
                    userApplication.getApplication_number(),
                    userApplication.getFullName(),
                    userApplication.getPhoneNumber(),
                    userApplication.getDateOfBirth(),
                    userApplication.getAge(),
                    userApplication.getEmail(),
                    userApplication.getAppliedOn(),
                    userApplication.isStatus(),
                    userApplication.isRejected()
            );
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;

    }
}
