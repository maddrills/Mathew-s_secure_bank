package com.mathew.bank.Mathewbank.DTO;


import java.time.LocalDate;
import java.util.Collection;

public class EmployeeDTO {


    private String phone_number;

    private String full_name;

    private String email;

    private LocalDate dateOfBirth;

    private double salary;

    private String password;

    private Collection<String> rolesName;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<String> getRolesName() {
        return rolesName;
    }

    public void setRolesName(Collection<String> rolesName) {
        this.rolesName = rolesName;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "phone_number='" + phone_number + '\'' +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                ", password='" + password + '\'' +
                ", rolesName=" + rolesName +
                '}';
    }
}
