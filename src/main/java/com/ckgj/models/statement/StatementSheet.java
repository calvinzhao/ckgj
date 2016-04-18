package com.ckgj.models.statement;

import java.util.Comparator;
import java.util.Date;

public class StatementSheet {
    public static Comparator<StatementSheet> StatementSheetComparator
            = new Comparator<StatementSheet>() {
        @Override
        public int compare(StatementSheet sheet1, StatementSheet sheet2) {
            Integer i1 = sheet1.period.getYear() * 100 + sheet1.period.getMonth();
            Integer i2 = sheet2.period.getYear() * 100 + sheet2.period.getMonth();
            return i1.compareTo(i2);
        }
    };

    private Long id;
    private String companyName;
    private Period period;
    private Float cash;
    private Float bankAccount;
    private Float revenue;
    private Float income;
    private Float cost;
    private Float supplierCost;
    private Float salaryCost;
    private Float taxCost;
    private Float receivable;
    private Float debt;
    private Float supplierDebt;
    private Float salaryDebt;
    private Float taxDebt;
    private Float asset;
    private Float liability;
    private Float equity;

    public StatementSheet() {}

    public StatementSheet(Statement statement) {
        id = statement.getId();
        cash = statement.getCash();
        bankAccount = statement.getBankAccount();
        revenue = statement.getRevenue();
        income = statement.getIncome();
        cost = statement.getCost();
        supplierCost = statement.getSupplierCost();
        salaryCost = statement.getSalaryCost();
        taxCost = statement.getTaxCost();
        receivable = statement.getReceivable();
        debt = statement.getReceivable();
        supplierDebt = statement.getSupplierDebt();
        salaryDebt = statement.getSalaryDebt();
        taxDebt = statement.getTaxDebt();
        asset = statement.getAsset();
        liability = statement.getLiability();
        equity = statement.getEquity();
        companyName = statement.getCompany().getName();
        period = new Period(statement.getPeriod());
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Float getCash() {
        return cash;
    }

    public void setCash(Float cash) {
        this.cash = cash;
    }

    public Float getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Float bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Float getRevenue() {
        return revenue;
    }

    public void setRevenue(Float revenue) {
        this.revenue = revenue;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getSupplierCost() {
        return supplierCost;
    }

    public void setSupplierCost(Float supplierCost) {
        this.supplierCost = supplierCost;
    }

    public Float getSalaryCost() {
        return salaryCost;
    }

    public void setSalaryCost(Float salaryCost) {
        this.salaryCost = salaryCost;
    }

    public Float getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(Float taxCost) {
        this.taxCost = taxCost;
    }

    public Float getReceivable() {
        return receivable;
    }

    public void setReceivable(Float receivable) {
        this.receivable = receivable;
    }

    public Float getDebt() {
        return debt;
    }

    public void setDebt(Float debt) {
        this.debt = debt;
    }

    public Float getSupplierDebt() {
        return supplierDebt;
    }

    public void setSupplierDebt(Float supplierDebt) {
        this.supplierDebt = supplierDebt;
    }

    public Float getSalaryDebt() {
        return salaryDebt;
    }

    public void setSalaryDebt(Float salaryDebt) {
        this.salaryDebt = salaryDebt;
    }

    public Float getTaxDebt() {
        return taxDebt;
    }

    public void setTaxDebt(Float taxDebt) {
        this.taxDebt = taxDebt;
    }

    public Float getAsset() {
        return asset;
    }

    public void setAsset(Float asset) {
        this.asset = asset;
    }

    public Float getLiability() {
        return liability;
    }

    public void setLiability(Float liability) {
        this.liability = liability;
    }

    public Float getEquity() {
        return equity;
    }

    public void setEquity(Float equity) {
        this.equity = equity;
    }
}
