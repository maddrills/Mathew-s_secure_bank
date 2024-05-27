package com.mathew.bank.Mathewbank.entity.userOnlyEntity.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "user")
public class User {

    private int id;

    private String userName;

    private String password;

    // TODO the two bellow are foreign keys and much be changed to match there entity
    private int userAccountId;

    private int branchId;

}
