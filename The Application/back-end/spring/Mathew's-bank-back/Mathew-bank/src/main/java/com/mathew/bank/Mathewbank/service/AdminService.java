package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmpRepo;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class AdminService {

    @Autowired
    private EmpRepo empRepo;


    public boolean addAnyEmployee(Employee employee){

        // sanity check
        if(employee == null) return false;

        return this.empRepo.addedAnyEmployee(employee);
    }

    public void addEmployeeAndDetails(
            String phone_number, String full_name, String email, LocalDate dateOfBirth, double salary,
            String password, Collection<String> rolesName
    ){
        //TODO a sanity check to make sure that user enters intended credentials

        Employee employee = new Employee(
                password,
                null,
                null,
                null
        );

        EmployeeDetails employeeDetails = new EmployeeDetails(
                phone_number,
                full_name,
                email,
                dateOfBirth,
                salary
        );

        employeeDetails.setEmployee(employee);

        try{
            empRepo.addAnEmployeeAndThereDetails(employeeDetails, rolesName);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public Role findARoleInDb(String role){

        try{
            this.empRepo.findRoleByRoleName(role);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

        return this.empRepo.findRoleByRoleName(role);
    }
}
