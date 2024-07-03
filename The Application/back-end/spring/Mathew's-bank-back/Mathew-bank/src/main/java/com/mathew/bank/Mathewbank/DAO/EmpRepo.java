package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.UserDetails;

import java.util.Collection;
import java.util.List;

public interface EmpRepo extends User_emp_commonRepo {

    public boolean addedAnyEmployee(Employee employee);

    public void addAnEmployeeAndThereDetails(EmployeeDetails employeeDetails,int adderId,  Collection<String> roleNames);

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

    public List<UserDetails> getAllUserAndThereInfo();

    public List<User> getAllUserAndThereInfoVViaUser();

    public List<UserApplication> getAllUserApplications();
    UserApplication getApplicationByIdNumber(int number);

    public void createAUserInBank(User user, String branch);

    public boolean acceptUserApplication(int applicationNumber,int employeeId);
    public boolean rejectUserApplication(int applicationNumber, int employeeId);

    public Employee getEmployeeById(int employeeId);

    public Collection<Employee> getEmployeeUnderBranch(int branchId);

    public boolean setClerkIntoBank(int managerID, int clerkId);

    public boolean removeManagerFromBank(int managerId, int adminId);

    public List<Employee> findAllEmployeesWhoAreNotManagersOrAdminsInBankBranch(int bankId);

    public Branch getBranchByEmployeeId(int employeeBranch);

    public boolean removeClerkFromBranchAdminControl(int bankId, int employeeId, int adminID);

    public boolean removeSubEmployeeUnderManagerInBankByManager(int subEmpId, int ManagerId);

    public Employee findEmployeeThatHasRoleName(String roleName);

    Collection<UserApplication> findAllApplicationsInBranchThatPointToAdmin(int bankId);
}
