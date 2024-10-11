package com.example.loan.servicing.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loanType;
    private double customerIncome;
    private double maxLoanAmount;
    private int loanTerm;
    private String collateral;
    private String bankName;  // Added field for bankName

    public LoanModel() {}

    public LoanModel(String loanType, double customerIncome, double maxLoanAmount, int loanTerm, String bankName) {
        this.loanType = loanType;
        this.customerIncome = customerIncome;
        this.maxLoanAmount = maxLoanAmount;
        this.loanTerm = loanTerm;
        this.bankName = bankName;  // Initialize bankName in constructor
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getCustomerIncome() {
        return customerIncome;
    }

    public void setCustomerIncome(double customerIncome) {
        this.customerIncome = customerIncome;
    }

    public double getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(double maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getCollateral() {
        return collateral;
    }

    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }

    public String getBankName() {
        return bankName;  // Getter for bankName
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;  // Setter for bankName
    }
}
