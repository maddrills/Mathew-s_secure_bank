package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    EmployeeService employeeService;

    //sends back a set of employees that work under a branch
    @GetMapping("/get-employees-by-branch")
    public Collection<EmployeeDTO> getEmployeesByBranch(@RequestParam int branchId, HttpServletResponse httpServletResponse){
        System.out.println(branchId);

        return this.employeeService.getEmployeesUnderABranch(branchId,httpServletResponse);
    }

    @GetMapping("/get-clerk-by-branch")
    public Collection<EmployeeDTO> getClerkByBranch(@RequestParam int branchId){
        System.out.println(branchId);

        return null;

    }

    @GetMapping("/get-employees-by-empId")
    public EmployeeDTO getEmployeeById(@RequestParam int employeeId){
        System.out.println(employeeId);
        return null;
    }

    @GetMapping("/get-employees-by-phone")
    public EmployeeDTO getEmployeeByPhoneNumber(@RequestParam String phoneNumber){
        System.out.println(phoneNumber);
        return null;
    }

    @GetMapping("/get-employees-by-email")
    public EmployeeDTO getEmployeeByEmail(@RequestParam String email){
        System.out.println(email);
        return null;
    }


    @PutMapping("/add-clark-to-branch")
    public boolean addClerkToBankBranch(@RequestParam int employeeClerk, Authentication authentication, HttpServletResponse response){

        System.out.println(employeeClerk);
        System.out.println(authentication.getName());

        return this.employeeService.addClarkToManagerBranch(employeeClerk,authentication,response);
    }
}
