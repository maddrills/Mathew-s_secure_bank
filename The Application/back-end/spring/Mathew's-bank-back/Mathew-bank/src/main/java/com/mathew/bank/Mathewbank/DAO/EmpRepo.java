package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;

import java.util.Collection;
import java.util.List;

public interface EmpRepo extends User_emp_commonRepo {

    public boolean addedAnyEmployee(Employee employee);

    public void addAnEmployeeAndThereDetails(EmployeeDetails employeeDetails,  Collection<String> roleNames);

    public void addARole(Role role);

    public void updateBankManager(Branch branch);

    public void addOeUpdateEmployeeBankAccount(int EmployeeId, int BankAccountNumber);

    public void removeRoleFromEmployee(int empId, String role);

    public void addARoleToAnEmployee(int empId, String role);

    public void addManagerToBranch(int managerId, int bankId);

    public void createBranch(Branch branch);
    public void createBranch(Branch branch, int manager);

    public List<Employee> getAllUsersFromDB();
    public List<Employee> getAllUsersFromDB(String roleName);

    public List<Branch> getAllBranchFromDB();
}
