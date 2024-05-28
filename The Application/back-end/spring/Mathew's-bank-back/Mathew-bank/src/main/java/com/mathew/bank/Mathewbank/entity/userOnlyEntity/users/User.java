package com.mathew.bank.Mathewbank.entity.userOnlyEntity.users;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
final public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_account_id")
    private UserAccounts userAccountId;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "branch_id")
    private Branch branchId;

    public User(String userName, String password, UserAccounts userAccountId, Branch branchId) {
        this.userName = userName;
        this.password = password;
        this.userAccountId = userAccountId;
        this.branchId = branchId;
    }

    public int getId() {
        return id;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
    }

    public UserAccounts getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(UserAccounts userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


