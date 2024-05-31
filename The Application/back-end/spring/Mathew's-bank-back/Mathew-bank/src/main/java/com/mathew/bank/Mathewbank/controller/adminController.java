package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    AdminService adminService;

    //add a role to the role table
    @PostMapping("/add_a_role")
    public List<RolesDto> addARole(@RequestBody List<RolesDto> rolesDto, HttpServletResponse response){

        return adminService.AddARoleToDb(rolesDto, response);

    }

    //adds an employee to db with or without authorization
    @PostMapping("/add_an_employee")
    public void addAnEmployee(@RequestBody EmployeeDTO employeeDTO){

        //TODO in sanity check make sure
        // Admin permission is blocked

        System.out.println(employeeDTO);

    }
}
