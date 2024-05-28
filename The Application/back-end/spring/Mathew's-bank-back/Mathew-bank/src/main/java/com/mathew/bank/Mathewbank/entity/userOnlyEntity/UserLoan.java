package com.mathew.bank.Mathewbank.entity.userOnlyEntity;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.LoanType;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_loan")
public class UserLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_loans_id")
    private int id;

    private LocalDate issueDate;

    private LocalDate approvedDate;

    private LocalDate returnDate;

    private User userId;

    private LoanType loanType;
}

