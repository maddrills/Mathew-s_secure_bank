package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.BranchDTO;
import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.DTO.UserAndDetailsDTO;
import com.mathew.bank.Mathewbank.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    AdminService adminService;

    //returns a list of all branches
    @GetMapping("/list_all_branches")
    public List<BranchDTO> getAllBranchesAndThereManagers(){

        return null;
    }

    //returns all users in the whole bank
    @GetMapping("/get_all_users")
    public List<UserAndDetailsDTO> getAllUsersAndCorrespondingBranch(){

        return null;
    }

    //returns all employees in all the bank branches
    @GetMapping("/get_all_employees")
    public List<EmployeeDTO> getAllEmployees(){

        return null;
    }

    //returns all managers in all the bank branches
    @GetMapping("/get_all_managers")
    public List<EmployeeDTO> getAllManager(){

        return null;
    }

    //returns all employees in all the bank branches
    @GetMapping("/get_all_clerk")
    public List<EmployeeDTO> getAllClerk(){

        return null;
    }

    //add a role to the role table
    @PostMapping("/add_a_role")
    public List<RolesDto> addARole(@RequestBody List<RolesDto> rolesDto, HttpServletResponse response){

        return adminService.AddARoleToDb(rolesDto, response);

    }

    //add a branch with an optional manager
    @PostMapping("/create_a_bank")
    public void createABranch(@RequestBody BranchDTO branchDTO){

        System.out.println(branchDTO);
    }

    @PutMapping("/manager_to_branch")
    public void addAManagerToBranch(@RequestParam int employeeAKAManager){

        System.out.println(employeeAKAManager);
    }

    //adds an employee to db with or without a ROLE
    @PostMapping("/add_an_employee")
    public String addAnEmployee(@RequestBody EmployeeDTO employeeDTO,  HttpServletResponse response){

        return this.adminService.addAnyEmployee(employeeDTO, response);

    }

    //update an employees role
    @PutMapping("/add_employee_permission")
    public void changeEmployeeRole(@RequestParam  int employeeId, @RequestBody List<RolesDto> rolesDto){

        System.out.println(employeeId);
        System.out.println(rolesDto);
    }

    //remove a permission
    @DeleteMapping("/remove_employee_permission")
    public void removeEmployeeRole(@RequestParam  int employeeId, @RequestBody List<RolesDto> rolesDto){

        System.out.println(employeeId);
        System.out.println(rolesDto);
    }
}
