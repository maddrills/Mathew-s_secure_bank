package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.BranchDTO;
import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.DTO.UserApplicationDTO;
import com.mathew.bank.Mathewbank.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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
    public EmployeeDTO getEmployeeById(@RequestParam int employeeId,HttpServletResponse response){
        System.out.println(employeeId);
        return this.employeeService.getEmployeeDetailsById(employeeId,response);
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

    @PatchMapping("/remove-clerk-from-managers-branch")
    public boolean removeClerkFromBankBranch(@RequestParam int employeeClerk, Authentication authentication, HttpServletResponse response){

        System.out.println("clerk -> "+employeeClerk);
        System.out.println("manager auth id -> " +authentication.getName());

        return this.employeeService.removeSubEmployeeFromBankByManager(employeeClerk, authentication, response);

    }


    @PatchMapping("/assign-application-to")
    public boolean assignApplicationTo(@RequestParam int employeeNumber,@RequestParam int applicationNumber, Authentication authentication, HttpServletResponse httpServletResponse){

        return this.employeeService.assignOrReassignUserApplication(employeeNumber,applicationNumber, authentication, httpServletResponse);
    }

    @GetMapping("/getAllAuthNames")
    public Collection<RolesDto> allRoles(){
        return this.employeeService.getAllRoles();
    }

    ///employee/getApplicationsUnderAnEmployee
    @GetMapping("/getApplicationsUnderAnEmployee")
    public List<UserApplicationDTO> getApplicationsUnderAnEmployee(@RequestParam int employeeId, HttpServletResponse response, Authentication authentication){
        return this.employeeService.getAllApplicationsAssignedToAnEmployee(employeeId, response, authentication);
    }

    ///employee/getApplicationsUnderAnEmployee
    @GetMapping("/getCurrentBranch")
    public Collection<BranchDTO> getCurrentBranch(HttpServletResponse response, Authentication authentication){
        return this.employeeService.getCurrentBranch(response, authentication);
    }


}
