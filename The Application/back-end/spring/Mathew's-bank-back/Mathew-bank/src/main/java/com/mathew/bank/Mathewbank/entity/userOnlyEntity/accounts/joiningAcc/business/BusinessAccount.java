package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.business;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "business_account")
public class BusinessAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_ac_no")
    private int id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "people_count_limit")
    private int peopleCountLimit;

    @Column(name = "min_amount")
    private double minAmount;

    @Column(name = "drw_limit")
    private double drawLimit;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "frozen")
    private boolean frozen;

    @Column(name = "next_interest_on")
    private LocalDateTime nextInterestOn;

    // TODO add minor modes
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "joint_account_id")
    private JointAccounts jointAccounts;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "creator")
    private User creator;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "account_type")
    private TimeSpace accountType;


    //map by join
    @OneToMany(
            mappedBy = "businessAccountNumber",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private BusinessPeople businessPeople;

    public BusinessAccount(double amount, int peopleCountLimit, double minAmount, double drawLimit, String businessName, boolean frozen, LocalDateTime nextInterestOn, JointAccounts jointAccounts, User creator, TimeSpace accountType) {
        this.amount = amount;
        this.peopleCountLimit = peopleCountLimit;
        this.minAmount = minAmount;
        this.drawLimit = drawLimit;
        this.businessName = businessName;
        this.frozen = frozen;
        this.nextInterestOn = nextInterestOn;
        this.jointAccounts = jointAccounts;
        this.creator = creator;
        this.accountType = accountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPeopleCountLimit() {
        return peopleCountLimit;
    }

    public void setPeopleCountLimit(int peopleCountLimit) {
        this.peopleCountLimit = peopleCountLimit;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getDrawLimit() {
        return drawLimit;
    }

    public void setDrawLimit(double drawLimit) {
        this.drawLimit = drawLimit;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public LocalDateTime getNextInterestOn() {
        return nextInterestOn;
    }

    public void setNextInterestOn(LocalDateTime nextInterestOn) {
        this.nextInterestOn = nextInterestOn;
    }

    public JointAccounts getJointAccounts() {
        return jointAccounts;
    }

    public void setJointAccounts(JointAccounts jointAccounts) {
        this.jointAccounts = jointAccounts;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public TimeSpace getAccountType() {
        return accountType;
    }

    public void setAccountType(TimeSpace accountType) {
        this.accountType = accountType;
    }

    public BusinessPeople getBusinessPeople() {
        return businessPeople;
    }

    public void setBusinessPeople(BusinessPeople businessPeople) {
        this.businessPeople = businessPeople;
    }

    public int getId() {
        return id;
    }
}
