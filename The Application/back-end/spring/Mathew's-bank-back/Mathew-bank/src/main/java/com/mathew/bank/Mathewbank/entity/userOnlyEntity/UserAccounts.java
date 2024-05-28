package com.mathew.bank.Mathewbank.entity.userOnlyEntity;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.BuildUp;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Checking;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import jakarta.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_acc_id")
    private int id;

    private Savings savings;

    private Checking checking;

    private BuildUp buildUp;

    private JointAccounts jointAccounts;

}
