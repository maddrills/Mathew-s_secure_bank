package com.mathew.bank.Mathewbank.entity.userOnlyEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private int toAccountNumber;

    private int fromAccountNumber;

    private boolean deposited;

    private double amount;

    //TODO change bellow for foreign key relationship
    private int userId;
}
