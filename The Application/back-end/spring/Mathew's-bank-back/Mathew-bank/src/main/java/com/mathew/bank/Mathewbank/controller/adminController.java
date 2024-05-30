package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/add_a_role")
    public void addARole(@RequestBody RolesDto rolesDto, HttpServletResponse response){

        System.out.println(Arrays.toString(rolesDto.getRoleNames()));

        response.setStatus(adminService.AddARoleToDb(rolesDto));

    }
}
