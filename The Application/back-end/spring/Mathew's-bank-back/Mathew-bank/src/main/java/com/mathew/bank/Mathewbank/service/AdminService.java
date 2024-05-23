package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmpRepo;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    EmpRepo empRepo;


    public boolean addAnyEmployee(Employee employee){

        // sanity check
        if(employee == null) return false;

        return this.empRepo.addedAnyEmployee(employee);
    }
}
