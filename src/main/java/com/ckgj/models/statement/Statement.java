package com.ckgj.models.statement;

import com.ckgj.models.company.Company;
import com.ckgj.models.user.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "statement")
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
    @Column(name = "period")
    private String period;
    @Column(name="cash") // float(25,2)
    private Float cash;
    @Column(name="bank_account")
    private Float bankAccount;
    @Column(name="revenue")
    private Float revenue;
    @Column(name="income")
    private Float income;
    @Column(name="cost")
    private Float cost;
    @Column(name="supplier_cost")
    private Float supplierCost;
    @Column(name="salary_cost")
    private Float salaryCost;
    @Column(name="tax_cost")
    private Float taxCost;
    @Column(name="receivable")
    private Float receivable;
    @Column(name="debt")
    private Float debt;
    @Column(name="supplier_debt")
    private Float supplierDebt;
    @Column(name="salary_debt")
    private Float salaryDebt;
    @Column(name="tax_debt")
    private Float taxDebt;
    @Column(name="asset")
    private Float asset;
    @Column(name="liability")
    private Float liability;
    @Column(name="equity")
    private Float equity;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public Statement withCash(Float value) {
        this.setCash(value);
        return this;
    }

    public Statement withBankAccount(Float value) {
        this.setBankAccount(value);
        return this;
    }

    public Statement withRevenue(Float value) {
        this.setRevenue(value);
        return this;
    }

    public Statement withIncome(Float value) {
        this.setIncome(value);
        return this;
    }

    public Statement withCost(Float value) {
        this.setCost(value);
        return this;
    }

    public Statement withSupplierCost(Float value) {
        this.setSupplierCost(value);
        return this;
    }

    public Statement withSalaryCost(Float value) {
        this.setSalaryCost(value);
        return this;
    }

    public Statement withTaxCost(Float value) {
        this.setTaxCost(value);
        return this;
    }

    public Statement withReceivable(Float value) {
        this.setReceivable(value);
        return this;
    }

    public Statement withDebt(Float value) {
        this.setDebt(value);
        return this;
    }

    public Statement withSupplierDebt(Float value) {
        this.setSupplierDebt(value);
        return this;
    }

    public Statement withSalaryDebt(Float value) {
        this.setSalaryDebt(value);
        return this;
    }

    public Statement withTaxDebt(Float value) {
        this.setTaxDebt(value);
        return this;
    }

    public Statement withAsset(Float value) {
        this.setAsset(value);
        return this;
    }

    public Statement withLiability(Float value) {
        this.setLiability(value);
        return this;
    }

    public Statement withEquity(Float value) {
        this.setEquity(value);
        return this;
    }

    public Statement withCompany(Company company) {
        this.setCompany(company);
        return this;
    }

    public Statement withPeriod(String period) {
        this.setPeriod(period);
        return this;
    }
}
