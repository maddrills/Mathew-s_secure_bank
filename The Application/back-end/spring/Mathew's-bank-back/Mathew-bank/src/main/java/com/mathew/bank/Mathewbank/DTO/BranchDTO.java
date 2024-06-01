package com.mathew.bank.Mathewbank.DTO;


import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;

public class BranchDTO {

    String branchName;

    String state;

    String country;

    boolean open;

    Employee branchManagerId;

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

    public Employee getBranchManagerId() {
        return branchManagerId;
    }

    public void setBranchManagerId(Employee branchManagerId) {
        this.branchManagerId = branchManagerId;
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "branchName='" + branchName + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", open=" + open +
                ", branchManagerId=" + branchManagerId +
                '}';
    }
}
