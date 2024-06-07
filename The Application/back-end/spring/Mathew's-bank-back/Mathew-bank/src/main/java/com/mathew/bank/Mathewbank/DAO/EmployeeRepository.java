package com.mathew.bank.Mathewbank.DAO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class EmployeeRepository implements EmpRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean addedAnyEmployee(Employee employee) {
        try{
            this.entityManager.persist(employee);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }



    @Override
    @Transactional
    public void  addAnEmployeeAndThereDetails(EmployeeDetails employeeDetails, Collection<String> roleNames) {

        //first checks if role is available only then does it persist else it will throw an exception and not persist
        roleNames.forEach(role -> employeeDetails.getEmployee().setARole(this.findRoleByRoleName(role)));

        entityManager.persist(employeeDetails);
    }


    //add a role by admin only
    @Override
    @Transactional
    public void addARole(Role role) {
        this.entityManager.persist(role);
    }


    @Override
    public Role findRoleByRoleName(String name) {

        TypedQuery<Role> query = this.entityManager.createQuery("SELECT R FROM Role as R WHERE R.role = :theId",Role.class);

        query.setParameter("theId","ROLE_"+name);

        return query.getSingleResult();
    }

    // TODO use this to find branch for admin
    @Override
    public Branch findBranchByName(String name) {
        return null;
    }

    @Override
    public Savings getSavingsAccountByNumber(int accountNumber) {

        TypedQuery<Savings> query = this.entityManager.createQuery("SELECT S FROM Savings AS S WHERE S.id = :accountNumber", Savings.class);

        query.setParameter("accountNumber",accountNumber);

        Savings savings = query.getSingleResult();

        System.out.println(savings.getAmount()-50);

        return savings;
    }

    //TODO IF ever required
    @Override
    public Branch getABranchById(int branchId) {
        return null;
    }


    @Override
    @Transactional
    public void updateBankManager(Branch branch) {

        this.entityManager.merge(branch);

    }

    @Override
    @Transactional
    public void addOeUpdateEmployeeBankAccount(int employeeId, int bankAccountNumber) {

        //find the employeeDetails by employee Is
        TypedQuery<EmployeeDetails> query = this.entityManager.createQuery(
                "SELECT ED FROM EmployeeDetails AS ED WHERE ED.employee IN " +
                        "(SELECT E FROM Employee AS E WHERE E.id = :empId)",
                EmployeeDetails.class
        );

        query.setParameter("empId",employeeId);

        EmployeeDetails employeeDetails = query.getSingleResult();

        System.out.println(employeeDetails.getEmail());

        //find the corresponding account number made to add employees salary
        Savings savings = this.entityManager.find(Savings.class,bankAccountNumber);

        employeeDetails.setSavings(savings);
        //finally update it
        this.entityManager.merge(employeeDetails);
    }

    private Employee getEmployeeById(final int empId){
        //find the employee by the id
        TypedQuery<Employee> emp = this.entityManager.createQuery("SELECT E FROM Employee E WHERE " +
                "E.id = :employeeId",Employee.class);
        emp.setParameter("employeeId", empId);

        return emp.getSingleResult();
    }
    private Branch getBranchById(final int branchId){
        //find the branch by the id
        TypedQuery<Branch> emp = this.entityManager.createQuery("SELECT B FROM Branch B WHERE " +
                "B.id = :branch",Branch.class);
        emp.setParameter("branch", branchId);

        return emp.getSingleResult();
    }

    @Override
    @Transactional
    public void removeRoleFromEmployee(int empId, String role) {
        //get the employee
        Employee employee = getEmployeeById(empId);
        //remove the object (Role) from the list of romes
        employee.getRoles().removeIf(a -> a.getRole().equals("ROLE_"+role));
        //persist the change
        this.entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void addARoleToAnEmployee(int empId, String role) {
        //get the employee
        Employee employee = getEmployeeById(empId);
        //find role in db
        Role dbRole = findRoleByRoleName(role);
        //remove the object (Role) from the list of romes
        employee.getRoles().add(dbRole);
        //persist the change
        this.entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void addManagerToBranch(int managerId, int bankId) {

        //get the employee and branch entity's
        Branch branch = getBranchById(bankId);
        Employee employee = getEmployeeById(managerId);

        //add branch to manager and manager to branch
        employee.setBankBranch(branch);
        branch.setBranchManager(employee);

        //finally persist
        this.entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void createBranch(Branch branch) {
        this.entityManager.persist(branch);
    }

    @Override
    @Transactional
    public void createBranch(Branch branch, int manager) {
        //find the employee In DB
        Employee employeeManager = this.getEmployeeById(manager);
        //update the branch field to have the respective manager
        branch.setBranchManager(employeeManager);
        //add the new branch to db
        this.entityManager.persist(branch);
    }

    @Override
    public List<Employee> getAllUsersFromDB() {

        TypedQuery<Employee> query = this.entityManager.createQuery("SELECT E FROM Employee AS E JOIN FETCH E.details", Employee.class);

        return query.getResultList();
    }

    @Override
    public List<Employee> getAllUsersFromDB(String roleName) {

        Role role = this.findRoleByRoleName(roleName);

        /*
         * IN tests is value of single valued path expression (persistent attribute of your entity)
         * in values you provided to query (or fetched via subquery).
         *
         * MEMBER OF tests is value you provided to query (or defined with expression) member of
         * values in some collection in your entity.*/

        TypedQuery<Employee> query = this.entityManager.createQuery(
                "SELECT E FROM Employee AS E JOIN FETCH E.details " +
                        "WHERE :roleEntity MEMBER OF E.roles",
                Employee.class);

        query.setParameter("roleEntity",role);

        return query.getResultList();
    }


//returns all the bank branches with their branch managers regardless if they exist or not
    @Override
    public List<Branch> getAllBranchFromDB() {

        TypedQuery<Branch> query = this.entityManager.createQuery(
                "SELECT B FROM Branch AS B"
                , Branch.class);

        return query.getResultList();
    }

    @Override
    public List<UserDetails> getAllUserAndThereInfo() {

        TypedQuery<UserDetails> userDetailsTypedQuery = this.entityManager.createQuery(
                "SELECT ud FROM UserDetails AS ud",
                UserDetails.class);

        return userDetailsTypedQuery.getResultList();
    }


    //get all user application
    @Override
    public List<UserApplication> getAllUserApplications() {

        TypedQuery<UserApplication> query = this.entityManager.createQuery(
                "SELECT ua FROM UserApplication AS ua"
                , UserApplication.class);

        return query.getResultList();

    }

    @Override
    @Transactional
    public void createAUserInBank(User user, String branchName) {

        //gets the branch the user will be added to
        Branch branch = this.findBranchByName(branchName);
        user.setBranchId(branch);
        this.entityManager.merge(user);
    }


    @Override
    public UserApplication getApplicationByIdNumber(int number) {

        return this.entityManager.find(UserApplication.class,number);
    }

    @Override
    @Transactional
    public void acceptUserApplication(int applicationNumber) {

        UserApplication userApplication = this.entityManager.find(UserApplication.class,applicationNumber);

        System.out.println(userApplication.getFullName());

        //create an employee and employee details

/*
        User user = new User(
                "Mathew Francis",
                "12345",
                AdminAccount,
                null
        );

        UserDetails userDetails = new UserDetails(
                "mathew francis",
                "3343350332",
                LocalDate.of(1998, 7, 21),
                30,
                "mat@admin",
                adminBankAccount
        );

        this.createAUserInBank(user,userDetails);
*/

        userApplication.setStatus(true);


    }

    @Override
    public void rejectUserApplication(int applicationNumber) {

    }


}
