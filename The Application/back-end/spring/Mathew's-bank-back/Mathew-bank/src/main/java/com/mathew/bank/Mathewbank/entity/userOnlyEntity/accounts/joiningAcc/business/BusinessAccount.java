package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.business;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "business_account")
public class BusinessAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_ac_no")
    private int id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "people_count_limit")
    private int peopleCountLimit;

    @Column(name = "min_amount")
    private double minAmount;

    @Column(name = "drw_limit")
    private double drawLimit;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "frozen")
    private boolean frozen;

    @Column(name = "next_interest_on")
    private LocalDateTime nextInterestOn;

    // TODO add minor modes
    private JointAccounts jointAccounts;

    private User creator;

    @OneToOne
    @JoinColumn(name = "account_type")
    private TimeSpace accountType;
}
