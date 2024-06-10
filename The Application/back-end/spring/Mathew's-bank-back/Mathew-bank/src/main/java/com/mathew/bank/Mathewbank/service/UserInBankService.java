package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.UserRepository;
import com.mathew.bank.Mathewbank.DTO.UserAccountDTO;
import com.mathew.bank.Mathewbank.DTO.UserAndDetailsDTO;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.UserDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInBankService {

    @Autowired
    private UserRepository userRepository;

    public UserAndDetailsDTO getUserAndUserDetailsFromService(int userID, HttpServletResponse response){

        User user;
        UserDetails userDetails;
        UserAccounts userAccounts;

        try{
            user = this.userRepository.getUserFromDb(userID);
            userDetails = user.getUserDetails();
            //get the account details like savings account build up etc etc
            userAccounts = user.getUserAccountId();

        }catch (Exception e){
            System.out.println(e);
            return null;
        }

        //sanity check
        if(userDetails == null) return null;



        //now get the credentials from entity to TDO
        return new UserAndDetailsDTO(
                userDetails.getId(),
                userDetails.getFullName(),
                null,
                null,
                userDetails.getFullName(),
                userDetails.getPhoneNumber(),
                userDetails.getDateOfBerth(),
                userDetails.getAge(),
                userDetails.getEmail(),
                new UserAccountDTO(
                        userAccounts.getId(),
                        userAccounts.getSavings() == null ? 0 : userAccounts.getSavings().getId() ,
                        userAccounts.getChecking() == null ? 0 : userAccounts.getChecking().getId(),
                        userAccounts.getBuildUp() == null ? 0 : userAccounts.getBuildUp().getId(),
                        userAccounts.getJointAccounts() == null ? 0 : userAccounts.getJointAccounts().getId(),
                        userAccounts.isFrozen()
                )
        );
    }

    public boolean createASavingsAccount(int userId, int accountId, HttpServletResponse response) {

        //sanity check
        if (userId <= 0 || accountId <= 0) {

            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return false;
        }

        //create a users bank account
        try{
            return this.userRepository.createASavingsAccountForUser(userId, accountId, response);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
