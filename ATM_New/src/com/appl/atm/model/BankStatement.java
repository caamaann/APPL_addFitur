/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

/**
 *
 * @author ACER
 */
public class BankStatement {
    private int idStatement;
    private int account;
    private String date;
    private String description;
    private int ref;
    private double withdrawal;
    private double deposit;
    private String DepositValidate;
    private double balance;

    
    /**
     * @return the idStatement
     */
    public int getIdStatement() {
        return idStatement;
    }

    /**
     * @param idStatement the idStatement to set
     */
    public void setIdStatement(int idStatement) {
        this.idStatement = idStatement;
    }

    /**
     * @return the account
     */
    public int getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(int account) {
        this.account = account;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the ref
     */
    public int getRef() {
        return ref;
    }

    /**
     * @param ref the ref to set
     */
    public void setRef(int ref) {
        this.ref = ref;
    }

    /**
     * @return the withdrawal
     */
    public double getWithdrawal() {
        return withdrawal;
    }

    /**
     * @param withdrawal the withdrawal to set
     */
    public void setWithdrawal(double withdrawal) {
        this.withdrawal = withdrawal;
    }

    /**
     * @return the deposit
     */
    public double getDeposit() {
        return deposit;
    }

    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    /**
     * @return the DepositValidate
     */
    public String getDepositValidate() {
        return DepositValidate;
    }

    /**
     * @param DepositValidate the DepositValidate to set
     */
    public void setDepositValidate(String DepositValidate) {
        this.DepositValidate = DepositValidate;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}
