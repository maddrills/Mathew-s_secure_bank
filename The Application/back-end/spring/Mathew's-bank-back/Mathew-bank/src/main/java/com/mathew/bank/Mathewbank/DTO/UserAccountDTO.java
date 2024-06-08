package com.mathew.bank.Mathewbank.DTO;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;

public class UserAccountDTO {

    private int userAccountID;

    private int savingsID;

    private int checkingID;

    private int buildUpID;

    private int jointAccountsID;

    private boolean frozen;

    public UserAccountDTO(int userAccountID, int savingsID, int checkingID, int buildUpID, int jointAccountsID, boolean frozen) {
        this.userAccountID = userAccountID;
        this.savingsID = savingsID;
        this.checkingID = checkingID;
        this.buildUpID = buildUpID;
        this.jointAccountsID = jointAccountsID;
        this.frozen = frozen;
    }

    public int getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(int userAccountID) {
        this.userAccountID = userAccountID;
    }

    public int getSavingsID() {
        return savingsID;
    }

    public void setSavingsID(int savingsID) {
        this.savingsID = savingsID;
    }

    public int getCheckingID() {
        return checkingID;
    }

    public void setCheckingID(int checkingID) {
        this.checkingID = checkingID;
    }

    public int getBuildUpID() {
        return buildUpID;
    }

    public void setBuildUpID(int buildUpID) {
        this.buildUpID = buildUpID;
    }

    public int getJointAccountsID() {
        return jointAccountsID;
    }

    public void setJointAccountsID(int jointAccountsID) {
        this.jointAccountsID = jointAccountsID;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
