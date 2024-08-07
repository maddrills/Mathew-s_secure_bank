package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity;

import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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


    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "branch_manager")
    Employee branchManager;

    @OneToMany(mappedBy = "branch",fetch = FetchType.LAZY,cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
    })
    private List<UserApplication> userApplications;

    public Branch() {
    }

    public Branch(String branchName, String state, String country, boolean open, Employee branchManager) {
        this.branchName = branchName;
        this.state = state;
        this.country = country;
        this.open = open;
        this.branchManager = branchManager;
    }


    public int getId() {
        return id;
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

    public List<UserApplication> getUserApplications() {
        return userApplications;
    }

    // convenience method
    public void setUserApplication(UserApplication userApplication) {
        if(this.userApplications == null){
            this.userApplications = new LinkedList<>();
        }
        this.userApplications.add(userApplication);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return id == branch.id;
    }
}
