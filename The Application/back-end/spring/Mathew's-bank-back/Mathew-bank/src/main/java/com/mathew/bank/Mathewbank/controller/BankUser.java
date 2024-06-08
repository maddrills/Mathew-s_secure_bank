package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DAO.UserRepository;
import com.mathew.bank.Mathewbank.DTO.UserAndDetailsDTO;
import com.mathew.bank.Mathewbank.service.UserInBankService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankUser")
public class BankUser {

    @Autowired
    private UserInBankService userInBankService;

    //view profile details
    // get them by user_id from JWT
    // https://stackoverflow.com/questions/54909509/accessing-jwt-token-from-a-spring-boot-rest-controller
    @GetMapping("/user-details")
    public UserAndDetailsDTO getUserDetails(int userId, HttpServletResponse response){
        //for now ill be using 2
        System.out.println(userId);
        return userInBankService.getUserAndUserDetailsFromService(userId, response);
    }


    //create savings account
    public boolean createASavingAccount(int userId){
        return false;
    }

    //create checking account
    public boolean createACheckingAccount(int userId){
        return false;
    }

    //create buildup account
    public boolean createABuildUpAccount(int userId){
        return false;
    }
}
