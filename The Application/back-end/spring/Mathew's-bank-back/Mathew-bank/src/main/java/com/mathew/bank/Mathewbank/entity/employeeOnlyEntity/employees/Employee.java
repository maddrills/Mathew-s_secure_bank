package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "employee")
final public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int id;

    @Column(name = "password")
    private String password;


    // self join for employees with managers
    @ManyToOne(fetch = FetchType.LAZY,cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
    })
    @JoinColumn(name = "reports_to")
    private Employee manager;


    // TODO add getter and convenience method
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
    })
    private Collection<Employee> employeeUnderManager;


    @OneToOne(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "branch_id")
    private Branch bankBranch;

//    @Column(name = "reports_to")
//    private int reportsTo;
//
//    @Column(name = "branch_id")
//    private int branchId;


    //ROLE
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    //The detach operation removes the entity from the persistent context. When we use CascadeType.DETACH, the child entity will also get removed from the persistent context.
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
            }
            //cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "emp_roles",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;


    public Employee() {
    }

    public Employee(String password, Employee manager, Branch bankBranch) {
        this.password = password;
        this.manager = manager;
        this.bankBranch = bankBranch;
    }

    public Employee(String password, Employee manager, Collection<Employee> employeeUnderManager, Branch bankBranch) {
        this.password = password;
        this.manager = manager;
        this.employeeUnderManager = employeeUnderManager;
        this.bankBranch = bankBranch;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Branch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(Branch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Collection<Employee> getEmployeeUnderManager() {
        return employeeUnderManager;
    }

    //convenience method
    public void addInEmployeeUnderManager(Employee employee){
        if(this.employeeUnderManager == null){
            employeeUnderManager = new HashSet<>();
        }
        employeeUnderManager.add(employee);
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    //TODO i want to get the role from the db then assign it to the employee
    public void setARole(Role role){
        if(this.roles == null){
            this.roles = new HashSet<>();
        }

        this.roles.add(role);
    }
}
