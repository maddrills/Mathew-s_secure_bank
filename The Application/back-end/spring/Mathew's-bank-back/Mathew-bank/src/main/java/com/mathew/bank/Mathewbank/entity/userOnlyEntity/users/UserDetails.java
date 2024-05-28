package com.mathew.bank.Mathewbank.entity.userOnlyEntity.users;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ud_id")
    private int id;

    private String fullName;

    private String phoneNumber;

    private LocalDate dateOfBerth;

    private int age;

    private String email;

    private User userId;
}
