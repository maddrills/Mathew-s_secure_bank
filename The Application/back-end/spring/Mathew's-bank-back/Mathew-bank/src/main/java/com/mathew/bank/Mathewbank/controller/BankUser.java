package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DAO.UserRepository;
import com.mathew.bank.Mathewbank.DTO.AllowedLoginOutputGeneric;
import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.UserAndDetailsDTO;
import com.mathew.bank.Mathewbank.service.EmployeeService;
import com.mathew.bank.Mathewbank.service.UserInBankService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankUser")
public class BankUser {

    @Autowired
    private UserInBankService userInBankService;

    @Autowired
    private EmployeeService employeeService;

    //view profile details
    // get them by user_id from JWT
    // https://stackoverflow.com/questions/54909509/accessing-jwt-token-from-a-spring-boot-rest-controller
    @GetMapping("/user-details")
    public UserAndDetailsDTO getUserDetails(@RequestParam int userId, HttpServletResponse response){
        //for now ill be using 2
        System.out.println(userId);
        return userInBankService.getUserAndUserDetailsFromService(userId, response);
    }


    //create savings account
    @PutMapping("/user-add-savings-account")
    public boolean createASavingAccount(@RequestParam int userId, @RequestParam int accountId, HttpServletResponse response){

        return this.userInBankService.createASavingsAccount(userId, accountId, response);
    }

    //TODO will be implemented after UI
    //create checking account
    public boolean createACheckingAccount(@RequestParam int userId, HttpServletResponse response){
        return false;
    }

    //create buildup account
    public boolean createABuildUpAccount(@RequestParam int userId, HttpServletResponse response){
        return false;
    }


    //returns two types of outputs either of type UserAndDetailsDTO or EmployeeDTO
    // the generic <T extends AllowedLoginOutputGeneric> is used to enforce the above condition
    @SuppressWarnings("unchecked")
    @GetMapping("/login")
    public <T extends AllowedLoginOutputGeneric> T loginUser(HttpServletResponse response){
        System.out.println("Default login rout");

        //find user by username
        // TODO get user details and account details by username
        // change the default username in production
        //String nameOrID = "Mathew Francis";
        String nameOrID = "1000001";

        boolean proceedWithUser = true;
        int employeeId = 0;
        try{
            employeeId = Integer.parseInt(nameOrID);
            proceedWithUser = false;
        }catch (Exception e){
            System.out.println(e.toString());
        }

        if(proceedWithUser){
            return (T) this.userInBankService.getUserAndUserDetailsFromService(nameOrID,response);
        }
        return (T) this.employeeService.getEmployeeDetailsById(employeeId,response);
    }
}
