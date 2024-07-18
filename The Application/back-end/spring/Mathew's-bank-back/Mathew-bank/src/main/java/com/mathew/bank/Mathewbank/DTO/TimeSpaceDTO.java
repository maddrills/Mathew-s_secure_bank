package com.mathew.bank.Mathewbank.DTO;

import jakarta.persistence.Column;

public class TimeSpaceDTO {

    private String accountType;

    private int second;

    private int min;

    private int hour;

    private int days;

    private int months;

    private int years;

    private double baseInterestRate;

    private boolean isAJointAccount;

    private double minStartingAmount;

    private double withdrawalCountLimit;

    private double moneyTransferLimit;

    private int baseLimit;

    private int monthlyDraw;

    private int dailyDraw;

    private int hourlyDraw;

    private int minutesDraw;

    private boolean periodic;

    public TimeSpaceDTO(String accountType, int second, int min, int hour, int days, int months, int years, double baseInterestRate, boolean isAJointAccount, double minStartingAmount, double withdrawalCountLimit, double moneyTransferLimit, int baseLimit, int monthlyDraw, int dailyDraw, int hourlyDraw, int minutesDraw,  boolean periodic) {
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
        this.periodic = periodic;
    }

    public int getMinutesDraw() {
        return minutesDraw;
    }

    public void setMinutesDraw(int minutesDraw) {
        this.minutesDraw = minutesDraw;
    }

    public int getHourlyDraw() {
        return hourlyDraw;
    }

    public void setHourlyDraw(int hourlyDraw) {
        this.hourlyDraw = hourlyDraw;
    }

    public int getDailyDraw() {
        return dailyDraw;
    }

    public void setDailyDraw(int dailyDraw) {
        this.dailyDraw = dailyDraw;
    }

    public int getMonthlyDraw() {
        return monthlyDraw;
    }

    public void setMonthlyDraw(int monthlyDraw) {
        this.monthlyDraw = monthlyDraw;
    }

    public int getBaseLimit() {
        return baseLimit;
    }

    public void setBaseLimit(int baseLimit) {
        this.baseLimit = baseLimit;
    }

    public double getMoneyTransferLimit() {
        return moneyTransferLimit;
    }

    public void setMoneyTransferLimit(double moneyTransferLimit) {
        this.moneyTransferLimit = moneyTransferLimit;
    }

    public double getWithdrawalCountLimit() {
        return withdrawalCountLimit;
    }

    public void setWithdrawalCountLimit(double withdrawalCountLimit) {
        this.withdrawalCountLimit = withdrawalCountLimit;
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

    public double getBaseInterestRate() {
        return baseInterestRate;
    }

    public void setBaseInterestRate(double baseInterestRate) {
        this.baseInterestRate = baseInterestRate;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public boolean isPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }

    @Override
    public String toString() {
        return "TimeSpaceDTO{" +
                "accountType='" + accountType + '\'' +
                ", second=" + second +
                ", min=" + min +
                ", hour=" + hour +
                ", days=" + days +
                ", months=" + months +
                ", years=" + years +
                ", baseInterestRate=" + baseInterestRate +
                ", isAJointAccount=" + isAJointAccount +
                ", minStartingAmount=" + minStartingAmount +
                ", withdrawalCountLimit=" + withdrawalCountLimit +
                ", moneyTransferLimit=" + moneyTransferLimit +
                ", baseLimit=" + baseLimit +
                ", monthlyDraw=" + monthlyDraw +
                ", dailyDraw=" + dailyDraw +
                ", hourlyDraw=" + hourlyDraw +
                ", minutesDraw=" + minutesDraw +
                '}';
    }
}
