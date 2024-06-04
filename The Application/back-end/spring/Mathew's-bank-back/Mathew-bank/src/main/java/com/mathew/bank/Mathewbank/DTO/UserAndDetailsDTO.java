package com.mathew.bank.Mathewbank.DTO;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;

import java.time.LocalDate;

public class UserAndDetailsDTO {

    private int userId;

    private String userName;

    private UserAccounts userAccountId;

    private Branch branchId;

    private String fullName;

    private String phoneNumber;

    private LocalDate dateOfBerth;

    private int age;

    private String email;

    public UserAndDetailsDTO() {
    }

    public UserAndDetailsDTO(int userId, String userName, UserAccounts userAccountId, Branch branchId,
                             String fullName, String phoneNumber, LocalDate dateOfBerth, int age, String email) {
        this.userId = userId;
        this.userName = userName;
        this.userAccountId = userAccountId;
        this.branchId = branchId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBerth = dateOfBerth;
        this.age = age;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserAccounts getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(UserAccounts userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Branch getBranchId() {
        return branchId;
    }

    public void setBranchId(Branch branchId) {
        this.branchId = branchId;
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
}
