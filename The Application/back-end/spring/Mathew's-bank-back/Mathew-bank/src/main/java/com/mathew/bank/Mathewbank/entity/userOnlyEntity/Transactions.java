package com.mathew.bank.Mathewbank.entity.userOnlyEntity;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
final public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String transactionDescription;

    @Column(name = "to_account_number")
    private int toAccountNumber;

    @Column(name = "from_account_number")
    private int fromAccountNumber;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "deposited")
    private boolean deposited;

    @Column(name = "amount")
    private double amount;

    //TODO change bellow for foreign key relationship
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    @JoinTable(name = "u_id")
    private UserAccounts userAccounts;

    public Transactions(String transactionDescription, int toAccountNumber, int fromAccountNumber, LocalDateTime transactionDate, boolean deposited, double amount, UserAccounts userAccounts) {
        this.transactionDescription = transactionDescription;
        this.toAccountNumber = toAccountNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.transactionDate = transactionDate;
        this.deposited = deposited;
        this.amount = amount;
        this.userAccounts = userAccounts;
    }

    public UserAccounts getUserAccounts() {
        return userAccounts;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public int getId() {
        return id;
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

}
