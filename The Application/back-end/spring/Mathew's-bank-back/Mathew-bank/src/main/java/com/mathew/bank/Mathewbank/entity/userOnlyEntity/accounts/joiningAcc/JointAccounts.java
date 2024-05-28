package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc;

import jakarta.persistence.*;

@Entity
@Table(name = "joint_accounts")
final public class JointAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joint_account_id")
    private int id;

    @Column(name = "active")
    private boolean active;

    public JointAccounts(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
