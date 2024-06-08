package com.mathew.bank.Mathewbank.entity.userOnlyEntity;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.BuildUp;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Checking;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import jakarta.persistence.*;

@Entity
@Table(name = "user_account")
final public class UserAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_acc_id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "s_ac_no")
    private Savings savings;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ack_ac_no")
    private Checking checking;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "b_ac_no")
    private BuildUp buildUp;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "joint_ac_id")
    private JointAccounts jointAccounts;

    @Column(name = "frozen")
    private boolean frozen;


    public UserAccounts() {
    }

    public UserAccounts(Savings savings, Checking checking, BuildUp buildUp, JointAccounts jointAccounts, boolean frozen) {
        this.savings = savings;
        this.checking = checking;
        this.buildUp = buildUp;
        this.jointAccounts = jointAccounts;
        this.frozen = frozen;
    }

    //basic account constructor
    public UserAccounts(boolean frozen) {
        this(null,null,null,null, frozen);
    }

    public int getId() {
        return id;
    }

    public Savings getSavings() {
        return savings;
    }

    public void setSavings(Savings savings) {
        this.savings = savings;
    }

    public Checking getChecking() {
        return checking;
    }

    public void setChecking(Checking checking) {
        this.checking = checking;
    }

    public BuildUp getBuildUp() {
        return buildUp;
    }

    public void setBuildUp(BuildUp buildUp) {
        this.buildUp = buildUp;
    }

    public JointAccounts getJointAccounts() {
        return jointAccounts;
    }

    public void setJointAccounts(JointAccounts jointAccounts) {
        this.jointAccounts = jointAccounts;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
