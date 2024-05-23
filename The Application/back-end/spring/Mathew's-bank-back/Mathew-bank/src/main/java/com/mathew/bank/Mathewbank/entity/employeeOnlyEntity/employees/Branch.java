package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees;

import jakarta.persistence.*;

@Entity
@Table(name = "branch")
final public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    int id;

    @Column(name = "branch_name")
    String branchName;

    @Column(name = "state")
    String state;

    @Column(name = "country")
    String country;

    @Column(name = "open")
    boolean open;


    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "branch_manager")
    Employee branchManager;

    public Branch(String branchName, String state, String country, boolean open, Employee branchManager) {
        this.branchName = branchName;
        this.state = state;
        this.country = country;
        this.open = open;
        this.branchManager = branchManager;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Employee getBranchManager() {
        return branchManager;
    }

    public void setBranchManager(Employee branchManager) {
        this.branchManager = branchManager;
    }
}
