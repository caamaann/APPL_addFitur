/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import static com.appl.atm.model.Constants.*;

/**
 *
 * @author Annazar
 */
public class Withdrawal extends Transaction {

    private int amount; // amount to withdraw
    private CashDispenser cashDispenser; // reference to cash dispenser

    // Withdrawal constructor
    public Withdrawal(int userAccountNumber, BankDatabase atmBankDatabase,
            CashDispenser atmCashDispenser) {

        // initialize superclass variables
        super(userAccountNumber, atmBankDatabase);
        cashDispenser = atmCashDispenser;
    }

    @Override
    public int execute() {
        Account account = getBankDatabase().getAccount(getAccountNumber());

        if (account.getAvailableBalance() < amount) {
            return BALANCE_NOT_ENOUGH;
        }

        if (account.getMaxWithdraw() < amount) {
            return REACH_LIMIT;
        }

	if (cashDispenser.isSufficientCashAvailable(amount)) {
	    cashDispenser.dispenseCash(amount);
	    account.debit(amount);
	    return WITHDRAW_SUCCESSFUL;
	} else {
	    return CASHDISPENSER_NOT_ENOUGH;
	}
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the cashDispenser
     */
    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    /**
     * @param cashDispenser the cashDispenser to set
     */
    public void setCashDispenser(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }

}
