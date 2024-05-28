package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "savings")
final public class Savings {

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

    @Column(name = "created")
    private LocalDate createdOn;

    @Column(name = "frozen")
    private boolean frozen;

    @OneToOne
    @JoinColumn(name = "account_type")
    private TimeSpace accountType;

    public Savings(boolean hold, boolean active, double amount, LocalDateTime nextInterestOn, boolean frozen, TimeSpace accountType) {
        this.hold = hold;
        this.active = active;
        this.amount = amount;
        this.nextInterestOn = nextInterestOn;
        this.frozen = frozen;
        this.accountType = accountType;
    }

    public LocalDate getCreatedOn() {
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
