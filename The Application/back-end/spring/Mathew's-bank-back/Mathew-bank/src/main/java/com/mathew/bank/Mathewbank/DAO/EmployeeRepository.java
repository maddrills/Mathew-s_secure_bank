package com.mathew.bank.Mathewbank.DAO;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;

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

        Branch branch = getBranchById(bankId);
        Employee employee = getEmployeeById(managerId);

        System.out.println(branch.getBranchName());
        System.out.println(employee.getId());

        employee.setBankBranch(branch);
        branch.setBranchManager(employee);
        this.entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void createBranch(Branch branch) {
        this.entityManager.persist(branch);
    }

    @Override
    public void createBranch(Branch branch, int manager) {
        System.out.println(branch.getBranchName()+" "+manager);
    }

}
