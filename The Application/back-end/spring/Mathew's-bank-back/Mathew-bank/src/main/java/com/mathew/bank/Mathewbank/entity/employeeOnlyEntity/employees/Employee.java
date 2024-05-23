package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees;

import jakarta.persistence.*;

import java.util.Collection;

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


    public Employee(String password, Employee manager, Branch bankBranch) {
        this.password = password;
        this.manager = manager;
        this.bankBranch = bankBranch;
    }
}
