package com.mathew.bank.Mathewbank.DTO;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

public class EmployeeDTO implements AllowedLoginOutputGeneric{


    private int empId;

    private String phone_number;

    private String full_name;

    private String email;

    private LocalDate dateOfBirth;

    private double salary;

    private String password;

    private int branchId;

    private Set<RolesDto> rolesName;


    public EmployeeDTO() {
    }

    public EmployeeDTO(int empId, String phone_number, String full_name, String email, LocalDate dateOfBirth, double salary, String password, Set<RolesDto> rolesName) {
        this.empId = empId;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.password = password;
        this.rolesName = rolesName;
    }

    public EmployeeDTO(int empId, String phone_number, String full_name, String email, LocalDate dateOfBirth, double salary, String password, int branchId, Set<RolesDto> rolesName) {
        this.empId = empId;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.password = password;
        this.branchId = branchId;
        this.rolesName = rolesName;
    }


    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public Collection<RolesDto> getRolesName() {
        return rolesName;
    }

    public void setRolesName(Set<RolesDto> rolesName) {
        this.rolesName = rolesName;
    }


    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId=" + empId +
                ", phone_number='" + phone_number + '\'' +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                ", password='" + password + '\'' +
                ", branchId=" + branchId +
                ", rolesName=" + rolesName +
                '}';
    }
}
