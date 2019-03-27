/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

/**
 *
 * @author Sophia Gianina Daeli
 */
public class Transfer extends Transaction {  
    
    private int amount; // amount to withdraw
    private CashDispenser cashDispenser; // reference to cash dispenser

    public Transfer(int userAccountNumber, BankDatabase atmBankDatabase,
            CashDispenser atmCashDispenser) {

        // initialize superclass variables
        super(userAccountNumber, atmBankDatabase);
        cashDispenser = atmCashDispenser;
    }
    
    @Override
    public int execute() {
        return 0;
    }    

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public void setCashDispenser(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }
}
