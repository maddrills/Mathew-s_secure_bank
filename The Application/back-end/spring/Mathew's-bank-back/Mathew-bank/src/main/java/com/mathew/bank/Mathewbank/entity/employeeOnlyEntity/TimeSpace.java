package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity;


import jakarta.persistence.Column;
import jakarta.persistence.Id;

final public class TimeSpace {

    @Id
    @Column(name = "account_type")
    private String accountType;

    @Column(name = "second")
    private int second;

    @Column(name = "min")
    private int min;

    @Column(name = "hour")
    private int hour;

    @Column(name = "days")
    private int days;

    @Column(name = "months")
    private int months;

    @Column(name = "years")
    private int years;

    @Column(name = "base_interest_rate")
    private int baseInterestRate;

    public TimeSpace(String accountType, int second, int min, int hour, int days, int months, int years, int baseInterestRate) {
        this.accountType = accountType;
        this.second = second;
        this.min = min;
        this.hour = hour;
        this.days = days;
        this.months = months;
        this.years = years;
        this.baseInterestRate = baseInterestRate;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getBaseInterestRate() {
        return baseInterestRate;
    }

    public void setBaseInterestRate(int baseInterestRate) {
        this.baseInterestRate = baseInterestRate;
    }
}
