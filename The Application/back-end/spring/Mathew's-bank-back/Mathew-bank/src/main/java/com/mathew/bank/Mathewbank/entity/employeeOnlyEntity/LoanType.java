package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "lone_type")
final public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lone_type_id")
    private int loneTypeId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "active")
    private boolean active;

    //only one employee can create a lone
    @OneToMany
    @JoinColumn(name = "created_by")
    private Employee createdByEmployee;

    public LoanType(double amount, LocalDate returnDate, boolean active, Employee createdByEmployee) {
        this.amount = amount;
        this.returnDate = returnDate;
        this.active = active;
        this.createdByEmployee = createdByEmployee;
    }

    public void setLoneTypeId(int loneTypeId) {
        this.loneTypeId = loneTypeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Employee getCreatedByEmployee() {
        return createdByEmployee;
    }

    public void setCreatedByEmployee(Employee createdByEmployee) {
        this.createdByEmployee = createdByEmployee;
    }
}
