package com.mathew.bank.Mathewbank.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserApplicationDTO {

    private String fullName;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private int age;

    private String email;

    private LocalDateTime appliedOn;

    public UserApplicationDTO() {
    }

    public LocalDateTime getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(LocalDateTime appliedOn) {
        this.appliedOn = appliedOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserApplicationDTO{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", appliedOn=" + appliedOn +
                '}';
    }
}
