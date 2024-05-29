package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;

import java.util.Collection;

public interface EmpRepo extends User_emp_commonRepo {

    public boolean addedAnyEmployee(Employee employee);

    public void addAnEmployeeAndThereDetails(EmployeeDetails employeeDetails,  Collection<String> roleNames);

    public void addARole(Role role);


    public void updateBankManager(Branch branch);
}
