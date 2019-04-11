/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

/**
 *
 * @author Annazar
 */
public abstract class Transaction {
    private int accountNumber; // indicates account involved
    private BankDatabase bankDatabase; // account database
    private BankStatement bankStatement;
   
    
    public Transaction(int userAccountNumber, BankDatabase atmBankDatabase, BankStatement theBankStatement) {
        accountNumber = userAccountNumber;
        bankDatabase = atmBankDatabase;
        bankStatement = theBankStatement;
    }
    public abstract int execute();

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return the bankDatabase
     */
    public BankDatabase getBankDatabase() {
        return bankDatabase;
    }

    /**
     * @param bankDatabase the bankDatabase to set
     */
    public void setBankDatabase(BankDatabase bankDatabase) {
        this.bankDatabase = bankDatabase;
    }

    /**
     * @return the bankStatement
     */
    public BankStatement getBankStatement() {
        return bankStatement;
    }

    /**
     * @param bankStatement the bankStatement to set
     */
    public void setBankStatement(BankStatement bankStatement) {
        this.bankStatement = bankStatement;
    }
    
    
}