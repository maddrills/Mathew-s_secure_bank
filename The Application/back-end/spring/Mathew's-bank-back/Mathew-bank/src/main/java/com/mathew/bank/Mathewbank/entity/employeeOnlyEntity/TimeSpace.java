package com.mathew.bank.Mathewbank.entity.employeeOnlyEntity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "time_space")
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
    private double baseInterestRate;

    @Column(name = "joint_account")
    private boolean isAJointAccount;

    @Column(name = "min_amount")
    private double minStartingAmount;

    @Column(name = "withdrawal_count_limit")
    private double withdrawalCountLimit;

    @Column(name = "money_transfer_limit")
    private double moneyTransferLimit;

    @Column(name = "base_limit")
    private int baseLimit;

    @Column(name = "monthly_draw")
    private int monthlyDraw;

    @Column(name = "daily_draw")
    private int dailyDraw;

    @Column(name = "hourly_draw")
    private int hourlyDraw;

    @Column(name = "minutes_draw")
    private int minutesDraw;

    //TODO may need to add a relationship back to the accounts


    public TimeSpace() {
    }

    public TimeSpace(String accountType, int second, int min, int hour, int days, int months, int years, double baseInterestRate, boolean isAJointAccount, double minStartingAmount) {
        this.accountType = accountType;
        this.second = second;
        this.min = min;
        this.hour = hour;
        this.days = days;
        this.months = months;
        this.years = years;
        this.baseInterestRate = baseInterestRate;
        this.isAJointAccount = isAJointAccount;
        this.minStartingAmount = minStartingAmount;
    }

    public TimeSpace(String accountType, int second, int min, int hour, int days, int months, int years, double baseInterestRate, boolean isAJointAccount, double minStartingAmount,
                     double withdrawalCountLimit, double moneyTransferLimit, int baseLimit, int monthlyDraw, int dailyDraw, int hourlyDraw, int minutesDraw) {
        this.accountType = accountType;
        this.second = second;
        this.min = min;
        this.hour = hour;
        this.days = days;
        this.months = months;
        this.years = years;
        this.baseInterestRate = baseInterestRate;
        this.isAJointAccount = isAJointAccount;
        this.minStartingAmount = minStartingAmount;

        this.withdrawalCountLimit = withdrawalCountLimit;
        this.moneyTransferLimit = moneyTransferLimit;
        this.baseLimit = baseLimit;
        this.monthlyDraw = monthlyDraw;
        this.dailyDraw = dailyDraw;
        this.hourlyDraw = hourlyDraw;
        this.minutesDraw = minutesDraw;
    }

    public double getWithdrawalCountLimit() {
        return withdrawalCountLimit;
    }

    public double getMoneyTransferLimit() {
        return moneyTransferLimit;
    }

    public int getBaseLimit() {
        return baseLimit;
    }

    public int getMonthlyDraw() {
        return monthlyDraw;
    }

    public int getDailyDraw() {
        return dailyDraw;
    }

    public int getHourlyDraw() {
        return hourlyDraw;
    }

    public int getMinutesDraw() {
        return minutesDraw;
    }

    public double getMinStartingAmount() {
        return minStartingAmount;
    }

    public void setMinStartingAmount(double minStartingAmount) {
        this.minStartingAmount = minStartingAmount;
    }

    public boolean isAJointAccount() {
        return isAJointAccount;
    }

    public void setAJointAccount(boolean AJointAccount) {
        isAJointAccount = AJointAccount;
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

    public double getBaseInterestRate() {
        return baseInterestRate;
    }

    public void setBaseInterestRate(double baseInterestRate) {
        this.baseInterestRate = baseInterestRate;
    }
}
