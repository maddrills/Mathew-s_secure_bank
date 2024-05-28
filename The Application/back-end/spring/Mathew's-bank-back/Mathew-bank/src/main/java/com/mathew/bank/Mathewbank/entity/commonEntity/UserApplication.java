package com.mathew.bank.Mathewbank.entity.commonEntity;


import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
final public class UserApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_number")
    private int application_number;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "applied_date")
    private LocalDateTime appliedOn;

    @Column(name = "status")
    private boolean status;

    @OneToOne
    @JoinColumn(name = "approved_by")
    private Employee approvedBy;

    @OneToOne
    @JoinColumn(name = "created_user_id")
    private User createdUser;

    @OneToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public UserApplication(String fullName, String phoneNumber, LocalDate dateOfBirth, int age, String email, LocalDateTime appliedOn, boolean status, Branch branch) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.appliedOn = appliedOn;
        this.status = status;
        this.branch = branch;
    }

    public void setApplication_number(int application_number) {
        this.application_number = application_number;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public LocalDateTime getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(LocalDateTime appliedOn) {
        this.appliedOn = appliedOn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
