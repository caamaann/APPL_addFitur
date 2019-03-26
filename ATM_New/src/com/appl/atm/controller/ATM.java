/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Account;
import com.appl.atm.model.BalanceInquiry;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.Admin;
import com.appl.atm.model.CashDispenser;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.DepositSlot;
import com.appl.atm.model.Transaction;
import com.appl.atm.model.Withdrawal;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

import java.text.ParseException;
import static com.appl.atm.model.Constants.*;

/**
 *
 * @author Annazar
 */
public class ATM {
    private boolean userAuthenticated;
    private boolean adminAuthenticated;//whether user is admin
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase; // account information database
    private int loginAttempt;
    private int currentPIN;


    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int TRANSFER = 4;
    private static final int PASSWORD = 5;
    private static final int BANK_STATEMENT = 6;
    private static final int TRANSFER_HISTORY = 7;
    private static final int WITHDRAWAL_HISTORY = 8;
    private static final int CHANGE_PIN = 9;
    private static final int EXIT = 0;
    
    public ATM() {
	userAuthenticated = false;
        adminAuthenticated = false;
	currentAccountNumber = 0;
	screen = new Screen();
	keypad = new Keypad();
	cashDispenser = new CashDispenser();
	depositSlot = new DepositSlot();
	bankDatabase = new BankDatabase();
    }

    // start ATM 
    public void run() throws ParseException{
	// welcome and authenticate user; perform transactions
        
        loginAttempt = 0;
        
	while (true) {
	    // loop while user is not yet authenticated
	    screen.displayMessageLine("\nWelcome!\n");
	    while (!userAuthenticated) {
		authenticateUser(); // authenticate user
	    }
            
            if (adminAuthenticated) {
                performAdmins();
            } else if(userAuthenticated){
                performTransactions();
                userAuthenticated = false;
                currentAccountNumber = 0;
                screen.displayMessageLine("\nThank you! Goodbye!");
            }
//                if (loginAttempt == 3) {
//                    screen.displayMessageLine("Your account has been blocked, please contact the bank");
//                    bankDatabase.blockAccount(currentAccountNumber); //blokir acconut
//                } else {
//                    performTransactions(); // user is now authenticated
//                    userAuthenticated = false; // reset before next ATM session
//                }
//                currentAccountNumber = 0; // reset before next ATM session
//            }
	}
    }

    // attempts to authenticate user against database
    private void authenticateUser() throws ParseException{
        loginAttempt = 0;
        
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        
        while(!adminAuthenticated && !userAuthenticated){
            screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
            int pin = keypad.getInput(); // input PIN

            currentAccountNumber = accountNumber;
            currentPIN = pin;

            // set userAuthenticated to boolean value returned by database
            adminAuthenticated
                    = bankDatabase.authenticateAdmin(accountNumber, pin);
            userAuthenticated
                    = bankDatabase.authenticateUser(accountNumber, pin);

            // check whether authentication succeeded
            if (adminAuthenticated) {
                performAdmins();
                return;
    //        } else if (adminAuthenticated){
    //            performAdmins();
            }else{
                if(userAuthenticated){
                    currentAccountNumber = accountNumber;
                    return;
                }else{
                    screen.displayMessageLine("Invalid account number or PIN or your "
                        + "account might be blocked. Please try again.");
                    loginAttempt++;
                }
                
                if(loginAttempt == 3){
                    screen.displayMessageLine("Failed to enter PIN 3 times, your account "
                    + "has been blocked! >:(");
                    screen.displayMessageLine("Please contact admin to unblock your account.");
                    bankDatabase.blockAccount(accountNumber);
                    return;
                }
            }
            
            
                
//            } else if (isAdmin(accountNumber)) {
//                screen.displayMessageLine("Invalid PIN");
//            }
//            if(loginAttempt == 3)
//                return;
        }
            
    }
    
    private boolean isAdmin(int adminAccountNumber) {
        return adminAccountNumber == 0;
    }
    
    // display the main menu and perform transactions
    private void performTransactions() {
	// local variable to store transaction currently being processed
	Transaction currentTransaction = null;
	TransactionController currentTransactionController = null;

	boolean userExited = false; // user has not chosen to exit

	// loop while user has not chosen option to exit system
	while (!userExited) {
	    // show main menu and get user selection
	    AccountController acc = new AccountController();   
            int mainMenuSelection = displayMainMenu();

	    // decide how to proceed based on user's menu selection
	    switch (mainMenuSelection) {
		// user chose to perform one of three transaction types
		case BALANCE_INQUIRY:

		    // initialize as new object of chosen type
		    currentTransaction
			    = createTransaction(mainMenuSelection);
		    currentTransactionController
			    = new BalanceInquiryController(currentTransaction, keypad, screen);
		    currentTransactionController.run(); // execute transaction
		    break;
		    
		case WITHDRAWAL:
		    currentTransaction
			    = createTransaction(mainMenuSelection);
		    currentTransactionController
			    = new WithdrawalController(currentTransaction, keypad, screen);
		    currentTransactionController.run(); // execute transaction
		    break;
		    
		case DEPOSIT:
		    currentTransaction
			    = createTransaction(mainMenuSelection);
		    currentTransactionController
			    = new DepositController(currentTransaction, keypad, screen);
		    currentTransactionController.run(); // execute transaction
		    break;
                case TRANSFER:
                    break;
                case PASSWORD:
                    break;
                case BANK_STATEMENT:
                    break;
                    
                case CHANGE_PIN :
                        bankDatabase.changePIN(currentAccountNumber);
                    break;
		case EXIT: // user chose to terminate session
		    screen.displayMessageLine("\nExiting the system...");
		    userExited = true; // this ATM session should end
		    break;
		    
		default: // 
		    screen.displayMessageLine(
			    "\nYou did not enter a valid selection. Try again.");
		    break;
	    }
	}
    }
    
    private void performAdmins() {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;

        boolean userExited = false; // user has not chosen to exit
    
    }

    // display the main menu and return an input selection
    private int displayMainMenu() {
	screen.displayMessageLine("\nMain menu : ");
	screen.displayMessageLine("1 - View my balance");
	screen.displayMessageLine("2 - Withdraw cash");
	screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("9 - Change PIN");
	screen.displayMessageLine("0 - Exit\n");
	screen.displayMessage("Enter a choice: ");
	return keypad.getInput(); // return user's selection
    }
    
    private int displayAdminMenu() {
        screen.displayMessageLine("\nAdmin menu:");
        screen.displayMessageLine("1 - Add Nasabah");
        screen.displayMessageLine("2 - Unblock Nasabah");
        screen.displayMessageLine("3 - Validate Deposit");
        screen.displayMessageLine("4 - See Money Dispenser");
        screen.displayMessageLine("5 - Add Tanggal");
        screen.displayMessageLine("0 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }

    private Transaction createTransaction(int type) {
	//Account acc = bankDatabase.getAccount(currentAccountNumber);
        
        Account acc = bankDatabase.getAccount(currentAccountNumber);
        Transaction temp = null;

	switch (type) {
	    case BALANCE_INQUIRY:
		temp = new BalanceInquiry(
			currentAccountNumber, bankDatabase);
		break;
	    case WITHDRAWAL:
		temp = new Withdrawal(
			currentAccountNumber, bankDatabase, cashDispenser);
		break;
	    case DEPOSIT:
		temp = new Deposit(
			currentAccountNumber, bankDatabase, depositSlot);
		break;
	}

	return temp;
    }

}
