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
public class BalanceInquiry extends Transaction {
    // BalanceInquiry constructor
    public BalanceInquiry(int userAccountNumber, BankDatabase atmBankDatabase,BankStatement bankStatement) {

	super(userAccountNumber, atmBankDatabase, bankStatement);
    } 

    @Override
    public int execute() {
	return 0;
    }
    
    public double getAvailableBalance() {
	return getBankDatabase().getAccount(getAccountNumber()).getAvailableBalance();
    }
    
    public double getTotalBalance() {
	return getBankDatabase().getAccount(getAccountNumber()).getTotalBalance();
    }
}