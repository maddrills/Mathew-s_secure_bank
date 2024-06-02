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

    //TODO
    //returns a list of all branches
    @GetMapping("/list_all_branches")
    public List<BranchDTO> getAllBranchesAndThereManagers(){

        return null;
    }

    //TODO
    //returns all users in the whole bank
    @GetMapping("/get_all_users")
    public List<UserAndDetailsDTO> getAllUsersAndCorrespondingBranch(){

        return null;
    }

    //TODO
    //returns all employees in all the bank branches
    @GetMapping("/get_all_employees")
    public List<EmployeeDTO> getAllEmployees(){

        return null;
    }

    //TODO
    //returns all managers in all the bank branches
    @GetMapping("/get_all_managers")
    public List<EmployeeDTO> getAllManager(){

        return null;
    }

    //TODO
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


    //add a branch with an optional manager TODO manager part need to be completed
    @PostMapping("/create_a_branch")
    public boolean createABranch(@RequestBody BranchDTO branchDTO){

        System.out.println(branchDTO);
        return this.adminService.createABranchWithOrWithoutManager(branchDTO);
    }


    @PutMapping("/manager_to_branch")
    public boolean addAManagerToBranch(@RequestParam int employeeAKAManager, @RequestParam int branchId ,HttpServletResponse response){

        return this.adminService.addAManagerToBranch(employeeAKAManager,branchId, response);
    }

    //adds an employee to db with or without a ROLE
    @PostMapping("/add_an_employee")
    public String addAnEmployee(@RequestBody EmployeeDTO employeeDTO,  HttpServletResponse response){

        return this.adminService.addAnyEmployee(employeeDTO, response);

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
