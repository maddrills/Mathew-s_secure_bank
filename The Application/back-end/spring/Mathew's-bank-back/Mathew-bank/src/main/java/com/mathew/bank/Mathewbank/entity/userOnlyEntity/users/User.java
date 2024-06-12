package com.mathew.bank.Mathewbank.entity.userOnlyEntity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "user")
final public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @JsonIgnore
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

    @OneToOne(mappedBy = "userId",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private UserDetails userDetails;

    @OneToOne(mappedBy = "createdUser")
    private UserApplication usersUserApplication;

    //ROLE
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    //The detach operation removes the entity from the persistent context. When we use CascadeType.DETACH, the child entity will also get removed from the persistent context.
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
            }
            //cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "u_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    public User() {
    }

    public User(String userName, String password, UserAccounts userAccountId, Branch branchId) {
        this.userName = userName;
        this.password = password;
        this.userAccountId = userAccountId;
        this.branchId = branchId;
    }

    public UserApplication getUsersUserApplication() {
        return usersUserApplication;
    }

    public void setUsersUserApplication(UserApplication usersUserApplication) {
        this.usersUserApplication = usersUserApplication;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setARole(Role role){
        if(this.roles == null){
            this.roles = new HashSet<Role>();
        }

        this.roles.add(role);
    }
}


