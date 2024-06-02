package com.mathew.bank.Mathewbank.DTO;


public class BranchDTO {

    String branchName;

    String state;

    String country;

    boolean open;

    int branchManagerId;

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
