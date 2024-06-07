package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.UserApplicationDTO;
import com.mathew.bank.Mathewbank.service.EmployeeService;
import com.mathew.bank.Mathewbank.service.UnRegUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class employeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UnRegUserService unRegUserService;

    //get all applications
    @GetMapping("/getAllApplications")
    public List<UserApplicationDTO> getAllUserApplications(HttpServletResponse response){

        return this.employeeService.getAllUserApplications(response);

    }

    @GetMapping("/getApplicationById")
    public UserApplicationDTO getUserApplicationById(@RequestParam int applicationNumber, HttpServletResponse response){

        return this.employeeService.getApplicationByIdNumber(applicationNumber, response);
    }

    @GetMapping("/getUserApplicationByPhoneOrEmail")
    public UserApplicationDTO getUserApplicationByPhoneNumberOrEmail(String phoneNumber, String email, HttpServletResponse response){

        //if both are null then return error
        if(phoneNumber == null && email == null) {response.setStatus(HttpServletResponse.SC_BAD_REQUEST); return null;}

        return this.unRegUserService.getUserApplication(phoneNumber, email, response);
    }

}
