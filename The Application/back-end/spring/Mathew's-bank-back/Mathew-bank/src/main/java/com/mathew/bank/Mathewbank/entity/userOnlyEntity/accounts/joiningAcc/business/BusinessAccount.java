package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.business;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;

public class BusinessAccount {

    private int id;

    private double amount;

    private int peopleCountLimit;

    private double minAmount;

    private double drawLimit;

    private String businessName;

    private boolean frozen;

    private JointAccounts jointAccounts;

    private User creator;

    private TimeSpace accountType;
}
