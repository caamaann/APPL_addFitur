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
public class Deposit extends Transaction {

    private double amount; // amount to deposit
    private DepositSlot depositSlot; // reference to deposit slot

    // Deposit constructor
    public Deposit(int userAccountNumber, BankDatabase atmBankDatabase,
	    DepositSlot atmDepositSlot) {

	// initialize superclass variables
	super(userAccountNumber, atmBankDatabase);
	depositSlot = atmDepositSlot;
    }

    @Override
    public int execute() {
	if (depositSlot.isEnvelopeReceived()) {
	    Account account = getBankDatabase().getAccount(getAccountNumber());
	    account.debit(amount);
	    return DEPOSIT_SUCCESSFUL;
	} else {
	    return ENVELOPE_IS_NOT_RECEIVED;
	}
    }

    /**
     * @return the amount
     */
    public double getAmount() {
	return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
	this.amount = amount;
    }

    /**
     * @return the depositSlot
     */
    public DepositSlot getDepositSlot() {
	return depositSlot;
    }

    /**
     * @param depositSlot the depositSlot to set
     */
    public void setDepositSlot(DepositSlot depositSlot) {
	this.depositSlot = depositSlot;
    }
}