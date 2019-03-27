/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.Transaction;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author Annazar
 */
public class DepositController extends TransactionController {

    private Deposit transaction;

    public DepositController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
	super(theKeypad, theScreen);
	transaction = (Deposit) theTransaction;
    }

    @Override
    public int run() {
	double amount = promptForDepositAmount();

	if (amount == DEPOSIT_CANCELED) {
	    getScreen().displayMessageLine("Canceling transaction...");
	} else {
	    transaction.setAmount(amount);
	    transaction.execute();
	    BankStatementController bankStatement = new BankStatementController();
            getScreen().displayMessage("Please insert a deposit envelope containing $");
	    getScreen().displayDollarAmount(amount);
	    getScreen().displayMessageLine("\n");
	    getScreen().displayMessageLine("Your envelope has been received.");
	    getScreen().displayMessageLine("NOTE: The money just deposited will not be available until we verify the amount of any enclosed cash and your checks clear.");
	}
	
	return 0;
    }

    // prompt user to enter a deposit amount in cents 
    private double promptForDepositAmount() {
	Screen screen = getScreen(); // get reference to screen

	// display the prompt
	screen.displayMessage("\nPlease enter a deposit amount in "
		+ "CENTS (or 0 to cancel): ");
	int input = getKeypad().getInput(); // receive input of deposit amount

	// check whether the user canceled or entered a valid amount
	if (input == DEPOSIT_CANCELED) {
	    return DEPOSIT_CANCELED;
	} else {
	    return (double) input / 100; // return dollar amount
	}
    }

}
