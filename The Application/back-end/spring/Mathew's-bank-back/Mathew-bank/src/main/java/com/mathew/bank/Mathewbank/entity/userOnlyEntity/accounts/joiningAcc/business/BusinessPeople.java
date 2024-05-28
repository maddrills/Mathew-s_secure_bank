package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.business;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import jakarta.persistence.*;

@Entity
@Table(name = "business_people")
public class BusinessPeople {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private int id;

    // TODO link all by relationships
    private JointAccounts userJointAccount;

    private BusinessAccount businessAccountNumber;
}
