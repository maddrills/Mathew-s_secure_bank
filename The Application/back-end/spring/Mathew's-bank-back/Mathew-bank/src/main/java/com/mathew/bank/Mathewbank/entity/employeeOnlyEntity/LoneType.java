package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "lone_type")
public class LoneType {

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
    private Employee createdByEmployee;
}
