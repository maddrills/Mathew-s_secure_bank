package com.mathew.bank.Mathewbank.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserApplicationDTO {
    private int applicationId;

    private String fullName;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private int age;

    private String email;

    private LocalDateTime appliedOn;

    private boolean status;

    private boolean rejected;

    private int branchId;

    private int assignedTo;

    private int approvedBy;

    private int createdUser;

    public UserApplicationDTO() {
    }

    public UserApplicationDTO(String fullName, String phoneNumber, LocalDate dateOfBirth, int age, String email, LocalDateTime appliedOn) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.appliedOn = appliedOn;
    }

    public UserApplicationDTO(String fullName, String phoneNumber, LocalDate dateOfBirth, int age, String email, LocalDateTime appliedOn, boolean status) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.appliedOn = appliedOn;
        this.status = status;
    }


    public UserApplicationDTO(int applicationId, String fullName, String phoneNumber, LocalDate dateOfBirth, int age, String email, LocalDateTime appliedOn, boolean status, boolean rejected, int branchId) {
        this.applicationId = applicationId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.appliedOn = appliedOn;
        this.status = status;
        this.rejected = rejected;
        this.branchId = branchId;
    }

    public UserApplicationDTO(int applicationId, String fullName, String phoneNumber, LocalDate dateOfBirth, int age, String email, LocalDateTime appliedOn, boolean status, boolean rejected, int branchId, int assignedTo) {
        this.applicationId = applicationId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.appliedOn = appliedOn;
        this.status = status;
        this.rejected = rejected;
        this.branchId = branchId;
        this.assignedTo = assignedTo;
    }

    public UserApplicationDTO(int applicationId, String fullName, String phoneNumber, LocalDate dateOfBirth, int age, String email, LocalDateTime appliedOn, boolean status, boolean rejected, int branchId, int assignedTo,int approvedBy, int createdUser) {
        this.applicationId = applicationId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.appliedOn = appliedOn;
        this.status = status;
        this.rejected = rejected;
        this.branchId = branchId;
        this.assignedTo = assignedTo;
        this.approvedBy = approvedBy;
        this.createdUser = createdUser;
    }


    public int getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }

    public int getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(int createdUser) {
        this.createdUser = createdUser;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
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
