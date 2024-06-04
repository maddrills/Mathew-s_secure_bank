package com.mathew.bank.Mathewbank.entity.userOnlyEntity.users;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_details")
final public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ud_id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "DOB")
    private LocalDate dateOfBerth;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "u_id")
    private User userId;

    public UserDetails() {
    }

    public UserDetails(String fullName, String phoneNumber, LocalDate dateOfBerth, int age, String email, User userId) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBerth = dateOfBerth;
        this.age = age;
        this.email = email;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBerth() {
        return dateOfBerth;
    }

    public void setDateOfBerth(LocalDate dateOfBerth) {
        this.dateOfBerth = dateOfBerth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
