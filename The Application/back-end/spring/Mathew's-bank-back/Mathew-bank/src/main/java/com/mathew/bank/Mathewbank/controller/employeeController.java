package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.UserApplicationDTO;
import com.mathew.bank.Mathewbank.service.EmployeeService;
import com.mathew.bank.Mathewbank.service.UnRegUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class employeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UnRegUserService unRegUserService;

    public UserApplicationDTO getAllUserApplications(){
        return null;
    }

    public UserApplicationDTO getUserApplicationById(int applicationNumber){
        return null;
    }

    @GetMapping("/getUserApplicationByPhoneOrEmail")
    public UserApplicationDTO getUserApplicationByPhoneNumberOrEmail(String phoneNumber, String email, HttpServletResponse response){

        //if both are null then return error
        if(phoneNumber == null && email == null) {response.setStatus(HttpServletResponse.SC_BAD_REQUEST); return null;}

        return this.unRegUserService.getUserApplication(phoneNumber, email, response);
    }

}
