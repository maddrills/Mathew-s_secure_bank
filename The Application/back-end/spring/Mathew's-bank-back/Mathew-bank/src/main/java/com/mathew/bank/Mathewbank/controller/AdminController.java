package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.BranchDTO;
import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.DTO.UserAndDetailsDTO;
import com.mathew.bank.Mathewbank.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    //returns a list of all branches
    @GetMapping("/list_all_branches_with_manager")
    public List<BranchDTO> getAllBranchesAndThereManagers(){

        return this.adminService.getAllBranches();
    }
    
    //returns all users in the whole bank
    @GetMapping("/get_all_users")
    public List<UserAndDetailsDTO> getAllUsersAndCorrespondingBranch(){

        return this.adminService.getAllUsersAndThereDetails();
    }

    //returns all employees in all the bank branches
    @GetMapping("/get_all_employees")
    public List<EmployeeDTO> getAllEmployees(){

        return this.adminService.allEmployeesOrByThereRole(null);
    }


    //returns all the employees according to their role
    @GetMapping("/get_all_employees_by_a_role_name")
    public List<EmployeeDTO> getEmployeeByRole(@RequestParam final String roleName){

        System.out.println(roleName);

        return this.adminService.allEmployeesOrByThereRole(roleName);
    }


    //add a role to the role table
    @PostMapping("/add_a_role")
    public List<RolesDto> addARole(@RequestBody List<RolesDto> rolesDto, HttpServletResponse response){

        return adminService.AddARoleToDb(rolesDto, response);

    }


    //add a branch with an optional manager
    @PostMapping("/create_a_branch")
    public boolean createABranch(@RequestBody BranchDTO branchDTO,HttpServletResponse response){

        System.out.println(branchDTO);
        return this.adminService.createABranchWithOrWithoutManager(branchDTO, response);
    }


    @PutMapping("/manager_to_branch")
    public boolean addAManagerToBranch(@RequestParam int employeeAKAManager, @RequestParam int branchId ,HttpServletResponse response){

        return this.adminService.addAManagerToBranch(employeeAKAManager,branchId, response);
    }

    //adds an employee to db with or without a ROLE
    @PostMapping("/add_an_employee")
    public String addAnEmployee(@RequestBody EmployeeDTO employeeDTO,  HttpServletResponse response, Authentication authentication){

        //check if auth

        return this.adminService.addAnyEmployee(employeeDTO, response,authentication);

    }

    //update an employees role
    @PatchMapping("/add_employee_permission")
    public List<RolesDto> changeEmployeeRole(@RequestParam  int employeeId, @RequestBody List<RolesDto> rolesDto,HttpServletResponse response){

        return this.adminService.addAnEmployeeRole(employeeId, rolesDto, response);
    }

    //remove a permission
    @PatchMapping("/remove_employee_permission")
    public List<RolesDto> removeEmployeeRole(@RequestParam  int employeeId, @RequestBody List<RolesDto> rolesDto, HttpServletResponse response){

        return this.adminService.changeEmployeePermission(employeeId,rolesDto,response);
    }
}
