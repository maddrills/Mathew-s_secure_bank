package com.mathew.bank.Mathewbank.DTO;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class UserDeepAccountDTO {

    private int id;

    private boolean hold;

    private boolean active;

    private double amount;

    private LocalDateTime nextInterestOn;

    private LocalDateTime createdOn;

    private boolean frozen;

    private boolean jointAccount;

    private LocalDateTime lastWithdrawalDate;

    private int periodicWithdrawalCount;

    private int WithdrawalCount;

    private TimeSpace accountType;

    private String accountTypeName;

    public UserDeepAccountDTO(int id, boolean hold, boolean active, double amount, LocalDateTime nextInterestOn, LocalDateTime createdOn, boolean frozen, boolean jointAccount, TimeSpace accountType) {
        this.id = id;
        this.hold = hold;
        this.active = active;
        this.amount = amount;
        this.nextInterestOn = nextInterestOn;
        this.createdOn = createdOn;
        this.frozen = frozen;
        this.jointAccount = jointAccount;
        this.accountType = accountType;
    }
    public UserDeepAccountDTO(int id, boolean hold, boolean active, double amount, LocalDateTime nextInterestOn, LocalDateTime createdOn, boolean frozen, boolean jointAccount, String accountTypeName) {
        this.id = id;
        this.hold = hold;
        this.active = active;
        this.amount = amount;
        this.nextInterestOn = nextInterestOn;
        this.createdOn = createdOn;
        this.frozen = frozen;
        this.jointAccount = jointAccount;
        this.accountTypeName = accountTypeName;
    }

    public UserDeepAccountDTO(int id, boolean hold, boolean active, double amount, LocalDateTime nextInterestOn, LocalDateTime createdOn, boolean frozen, boolean jointAccount, String accountTypeName, LocalDateTime lastWithdrawalDate, int periodicWithdrawalCount, int WithdrawalCount) {
        this.id = id;
        this.hold = hold;
        this.active = active;
        this.amount = amount;
        this.nextInterestOn = nextInterestOn;
        this.createdOn = createdOn;
        this.frozen = frozen;
        this.jointAccount = jointAccount;
        this.accountTypeName = accountTypeName;
        this.lastWithdrawalDate = lastWithdrawalDate;
        this.periodicWithdrawalCount = periodicWithdrawalCount;
        this.WithdrawalCount = WithdrawalCount;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHold() {
        return hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getNextInterestOn() {
        return nextInterestOn;
    }

    public void setNextInterestOn(LocalDateTime nextInterestOn) {
        this.nextInterestOn = nextInterestOn;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isJointAccount() {
        return jointAccount;
    }

    public void setJointAccount(boolean jointAccount) {
        this.jointAccount = jointAccount;
    }

    public TimeSpace getAccountType() {
        return accountType;
    }

    public void setAccountType(TimeSpace accountType) {
        this.accountType = accountType;
    }

    public LocalDateTime getLastWithdrawalDate() {
        return lastWithdrawalDate;
    }

    public void setLastWithdrawalDate(LocalDateTime lastWithdrawalDate) {
        this.lastWithdrawalDate = lastWithdrawalDate;
    }

    public int getWithdrawalCount() {
        return WithdrawalCount;
    }

    public void setWithdrawalCount(int withdrawalCount) {
        WithdrawalCount = withdrawalCount;
    }

    public int getPeriodicWithdrawalCount() {
        return periodicWithdrawalCount;
    }

    public void setPeriodicWithdrawalCount(int periodicWithdrawalCount) {
        this.periodicWithdrawalCount = periodicWithdrawalCount;
    }

    @Override
    public String toString() {
        return "UserDeepAccountDTO{" +
                "id=" + id +
                ", hold=" + hold +
                ", active=" + active +
                ", amount=" + amount +
                ", nextInterestOn=" + nextInterestOn +
                ", createdOn=" + createdOn +
                ", frozen=" + frozen +
                ", jointAccount=" + jointAccount +
                ", lastWithdrawalDate=" + lastWithdrawalDate +
                ", periodicWithdrawalCount=" + periodicWithdrawalCount +
                ", WithdrawalCount=" + WithdrawalCount +
                ", accountType=" + accountType +
                ", accountTypeName='" + accountTypeName + '\'' +
                '}';
    }
}
