package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "emp_details")
final public class EmployeeDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ed_id")
    private int id;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @Column(name = "salary")
    private double salary;


    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "salary_account")
    private Savings savings;


    public EmployeeDetails() {
    }

    public EmployeeDetails(String phone_number, String fullName, String email, LocalDate dateOfBirth, double salary, Savings savings) {
        this.phone_number = phone_number;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.savings = savings;
    }

    public Savings getSavings() {
        return savings;
    }

    public void setSavings(Savings savings) {
        this.savings = savings;
    }

    public int getId() {
        return id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
