package com.mathew.bank.Mathewbank.DTO;

import java.time.LocalDateTime;

public class TransactionsDTO {

    private int id;

    private String transactionDescription;

    private int toAccountNumber;

    private int fromAccountNumber;

    private LocalDateTime transactionDate;

    private boolean deposited;

    private double amount;

    private double remainingAmount;

    public TransactionsDTO(int id, String transactionDescription, int toAccountNumber, int fromAccountNumber, LocalDateTime transactionDate, boolean deposited, double amount, double remainingAmount) {
        this.id = id;
        this.transactionDescription = transactionDescription;
        this.toAccountNumber = toAccountNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.transactionDate = transactionDate;
        this.deposited = deposited;
        this.amount = amount;
        this.remainingAmount = remainingAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public int getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(int toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public int getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(int fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public boolean isDeposited() {
        return deposited;
    }

    public void setDeposited(boolean deposited) {
        this.deposited = deposited;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
}
