package com.mathew.bank.Mathewbank.entity.userOnlyEntity;

public class Transactions {

    private int id;

    private int toAccountNumber;

    private int fromAccountNumber;

    private boolean deposited;

    private double amount;

    //TODO change bellow for foreign key relationship
    private int userId;
}
