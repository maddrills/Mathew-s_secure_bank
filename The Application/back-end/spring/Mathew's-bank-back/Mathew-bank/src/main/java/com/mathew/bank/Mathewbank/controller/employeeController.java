package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.UserApplicationDTO;
import com.mathew.bank.Mathewbank.service.EmployeeService;
import com.mathew.bank.Mathewbank.service.UnRegUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //TODO entertain JWT in here  use this link
    // https://stackoverflow.com/questions/54909509/accessing-jwt-token-from-a-spring-boot-rest-controller
    @PatchMapping("/acceptApplication")
    public boolean acceptApplication(@RequestParam int applicationNumber, HttpServletResponse servletResponse){

        System.out.println(applicationNumber);

        return this.employeeService.acceptAnApplicationNyId(applicationNumber, 0,servletResponse);
    }
    @PatchMapping("/rejectApplication")
    public boolean rejectApplication(@RequestParam int applicationNumber, HttpServletResponse servletResponse){

        return this.employeeService.rejectApplication(applicationNumber, 0,servletResponse);
    }

}
