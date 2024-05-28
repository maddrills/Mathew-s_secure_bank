package com.mathew.bank.Mathewbank.entity.userOnlyEntity.users;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;

    private String userName;

    private String password;

    private UserAccounts userAccountId;

    private Branch branchId;

}
