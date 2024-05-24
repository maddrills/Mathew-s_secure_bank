package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;

public interface EmpRepo extends User_emp_commonRepo {

    public boolean addedAnyEmployee(Employee employee);

    public void addAnEmployeeAndThereDetails(Employee employee, EmployeeDetails employeeDetails);

    public void addARole(Role role);
}
