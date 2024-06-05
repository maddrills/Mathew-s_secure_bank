package com.mathew.bank.Mathewbank.DTO;


import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;

public class BranchDTO {
    int branchId;

    String branchName;

    String state;

    String country;

    boolean open;

    int branchManagerId;

    public BranchDTO() {
    }

    public BranchDTO(String branchName, String state, String country, boolean open, int branchManagerId) {
        this.branchName = branchName;
        this.state = state;
        this.country = country;
        this.open = open;
        this.branchManagerId = branchManagerId;
    }

    public BranchDTO(int branchId, String branchName, String state, String country, boolean open, int branchManagerId) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.state = state;
        this.country = country;
        this.open = open;
        this.branchManagerId = branchManagerId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    public int getBranchManagerId() {
        return branchManagerId;
    }

    public void setBranchManagerId(int branchManagerId) {
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
