package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmpRepo;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;

@Service
public class AdminService {

    @Autowired
    EmpRepo empRepo;


    public boolean addAnyEmployee(Employee employee){

        // sanity check
        if(employee == null) return false;

        return this.empRepo.addedAnyEmployee(employee);
    }

    public void addEmployeeAndDetails(
            String phone_number, String full_name, String email, Date dateOfBirth, double salary,
            String password, Collection<Role> roles
    ){
        //TODO a sanity check to make sure that user enters intended credentials

        Employee employee = new Employee(
                password,
                null,
                null,
                null,
                roles
        );

        EmployeeDetails employeeDetails = new EmployeeDetails(
                phone_number,
                full_name,
                email,
                dateOfBirth,
                salary
        );

        empRepo.addAnEmployeeAndThereDetails(employee,employeeDetails);
    }
}
