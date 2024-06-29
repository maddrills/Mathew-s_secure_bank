package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
final public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_account_number")
    private int id;

    @Column(name = "hold")
    private boolean hold;

    @Column(name = "active")
    private boolean active;

    @Column(name = "amount")
    private double amount;

    @Column(name = "next_interest_on")
    private LocalDateTime nextInterestOn;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "frozen")
    private boolean frozen;

    @Column(name = "is_joint_account")
    private boolean jointAccount;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "account_type")
    private TimeSpace accountType;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_accounts_id")
    private UserAccounts userAccounts;

    public Account() {
    }

    public Account(boolean hold, boolean active, double amount, LocalDateTime nextInterestOn, boolean frozen, TimeSpace accountType, LocalDateTime createdOn, boolean jointAccount) {
        this.hold = hold;
        this.active = active;
        this.amount = amount;
        this.nextInterestOn = nextInterestOn;
        this.frozen = frozen;
        this.accountType = accountType;
        this.createdOn = createdOn;
        this.jointAccount = jointAccount;
    }

    public UserAccounts getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(UserAccounts userAccounts) {
        this.userAccounts = userAccounts;
    }

    public boolean isJointAccount() {
        return jointAccount;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public int getId() {
        return id;
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

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public TimeSpace getAccountType() {
        return accountType;
    }

    public void setAccountType(TimeSpace accountType) {
        this.accountType = accountType;
    }
}
