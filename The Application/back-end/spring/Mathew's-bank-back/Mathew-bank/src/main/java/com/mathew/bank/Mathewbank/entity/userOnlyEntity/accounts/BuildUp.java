package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;

import java.time.LocalDate;

public class BuildUp {

    private int SavingsAccountNumber;

    private boolean hold;

    private boolean active;

    private double amount;

    private LocalDate nextInterestOn;

    private LocalDate canWithdrawOn;

    private boolean frozen;

    private TimeSpace accountType;

}
