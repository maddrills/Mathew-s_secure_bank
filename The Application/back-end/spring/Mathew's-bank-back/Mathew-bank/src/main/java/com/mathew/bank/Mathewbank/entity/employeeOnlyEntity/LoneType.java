package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity;

import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Date;


//@Entity
//@Table(name = "lone_type")
public class LoneType {

    private int loneTypeId;

    private double amount;

    private Date returnDate;

    private boolean active;

    private Employee createdByEmployee;
}
