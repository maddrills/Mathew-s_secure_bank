package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.spouse;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "spouse_account")
final public class SpouseAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sa_ac_no")
    private int id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "frozen")
    private boolean frozen;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "joint_account_id")
    private JointAccounts jointAccountId;

    @Column(name = "next_interest_on")
    private LocalDateTime nextInterestOn;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "account_type")
    private TimeSpace accountType;

    //map by
    @OneToOne(
            mappedBy = "husbandJointAccount",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private Spouse husbandJointAccount;

    @OneToOne(
            mappedBy = "wife_joint_account",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private Spouse wifeJointAccount;

    public SpouseAccount(double amount, boolean frozen, JointAccounts jointAccountId, LocalDateTime nextInterestOn, TimeSpace accountType) {
        this.amount = amount;
        this.frozen = frozen;
        this.jointAccountId = jointAccountId;
        this.nextInterestOn = nextInterestOn;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public JointAccounts getJointAccountId() {
        return jointAccountId;
    }

    public void setJointAccountId(JointAccounts jointAccountId) {
        this.jointAccountId = jointAccountId;
    }

    public TimeSpace getAccountType() {
        return accountType;
    }

    public void setAccountType(TimeSpace accountType) {
        this.accountType = accountType;
    }

    public LocalDateTime getNextInterestOn() {
        return nextInterestOn;
    }

    public void setNextInterestOn(LocalDateTime nextInterestOn) {
        this.nextInterestOn = nextInterestOn;
    }
}
