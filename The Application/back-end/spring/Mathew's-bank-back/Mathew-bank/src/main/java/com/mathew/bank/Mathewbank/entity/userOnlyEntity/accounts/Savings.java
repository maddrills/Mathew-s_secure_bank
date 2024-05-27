package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts;

import java.sql.Date;
import java.time.LocalDate;

//TODO make this after Admin is made
final public class Savings {

    private int SavingsAccountNumber;

    private boolean hold;

    private boolean active;

    private double amount;

    private LocalDate nextInterestOn;

    private boolean frozen;

    private String accountType;

}
