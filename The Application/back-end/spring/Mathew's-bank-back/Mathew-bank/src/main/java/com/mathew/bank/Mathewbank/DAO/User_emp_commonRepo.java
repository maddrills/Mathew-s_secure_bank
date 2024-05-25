package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;

//extended by empRepo and userRepo
public interface User_emp_commonRepo {

    public Role findRoleByRoleName(String name);

}
