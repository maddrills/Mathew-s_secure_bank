package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees;

import jakarta.persistence.*;

import java.sql.Date;

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
    private String full_name;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(name = "salary")
    private double salary;


    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private Employee emp_id;


    public EmployeeDetails(String phone_number, String full_name, String email, Date dateOfBirth, double salary, Employee emp_id) {
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.emp_id = emp_id;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Employee emp_id) {
        this.emp_id = emp_id;
    }
}
