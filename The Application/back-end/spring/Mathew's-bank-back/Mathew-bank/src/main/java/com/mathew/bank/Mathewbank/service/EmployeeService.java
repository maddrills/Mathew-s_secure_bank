package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


}
