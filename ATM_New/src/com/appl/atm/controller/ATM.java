/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.BalanceInquiry;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.BankStatement;
import com.appl.atm.model.CashDispenser;
import com.appl.atm.model.Deposit;
import com.appl.atm.model.DepositSlot;
import com.appl.atm.model.Transaction;
import com.appl.atm.model.Withdrawal;
import com.appl.atm.model.Transfer;

import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import com.appl.atm.view.ShowCashDispenser;
import com.appl.atm.view.AdminView;
import java.io.IOException;

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
    private AdminController adminController;
    private AccountController accountController;
    private ShowCashDispenser showCashDispenser;
    private TanggalController tanggalController;
    private AdminView adminView;
    private BankStatement bankStatement;
    private BankStatementController bsc;
    

    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int TRANSFER = 4;
    private static final int PASSWORD = 5;
    private static final int BANK_STATEMENT = 6;
    private static final int TRANSFER_HISTORY = 7;
    private static final int WITHDRAWAL_HISTORY = 8;
    private static final int EXIT = 0;

    private static final int ADD_NASABAH = 1;
    private static final int UNBLOCK = 2;
    private static final int VALIDATE = 3;
    private static final int SEE_MONEY_DISPENSER = 4;
    private static final int ADD_MONEY_DISPENSER = 5;
    private static final int ADD_TANGGAL = 6;

    private int loginAttempt = 0;
    private int currentPIN;

    public ATM() {
        userAuthenticated = false;
        adminAuthenticated = false;
        currentAccountNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();
        adminController = new AdminController(bankDatabase, cashDispenser, keypad, screen);
        accountController = new AccountController(bankDatabase, keypad, screen);
        showCashDispenser = new ShowCashDispenser();
        tanggalController = new TanggalController();
        adminView = new AdminView();
        bankStatement = new BankStatement(currentAccountNumber);
    
    }

    // start ATM 
    public void run() throws IOException {
        // welcome and authenticate user; perform transactions
        while (true) {
            // loop while user is not yet authenticated
            screen.displayMessageLine("\nWelcome!\n");
            while ((!userAuthenticated) && loginAttempt < 3) {
                authenticateUser(); // authenticate user
            }

            if (adminAuthenticated) {
                performAdmins();
            } else {
                if (loginAttempt == 3) {
                    screen.displayMessageLine("Your account has been blocked, please contact the bank");
                    bankDatabase.blockAccount(currentAccountNumber); //blokir acconut
                    loginAttempt = 0;
                } else {
                    performTransactions(); // user is now authenticated
                    userAuthenticated = false; // reset before next ATM session
                }
                currentAccountNumber = 0; // reset before next ATM session
            }

            userAuthenticated = false; // reset before next ATM session
            adminAuthenticated = false; // reset before next ATM session
            currentAccountNumber = 0; // reset before next ATM session
            screen.displayMessageLine("\nThank you! Goodbye!");
        }
    }

    // attempts to authenticate user against database
    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
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
        if (userAuthenticated) {
            currentAccountNumber = accountNumber; // save user's account #
            loginAttempt = 0;
//        } else if (adminAuthenticated){
//            performAdmins();
        } else if (!bankDatabase.isUserExist(accountNumber) && !isAdmin(accountNumber)) {
            screen.displayMessageLine("Invalid user Account Number");
            loginAttempt = 0;
        } else if (!isAdmin(accountNumber)) {
            if (loginAttempt < 2) {
                screen.displayMessageLine(
                        "Invalid PIN. Please try again. You have " + (2 - loginAttempt) + " attempt(s) remaining.");
            }
            loginAttempt++;
        } else if (isAdmin(accountNumber)) {
            screen.displayMessageLine("Invalid PIN");
        }
    }

    private boolean isAdmin(int adminAccountNumber) {
        return adminAccountNumber == 0;
    }

    // display the main menu and perform transactions
    private void performTransactions() throws IOException {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;
        TransactionController currentTransactionController = null;

        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
            // show main menu and get user selection
//	    AccountController acc = new AccountController();   
            int mainMenuSelection = accountController.displayMainMenu(currentAccountNumber);

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
                    boolean isNotStudent = checkAccountType();

                    if (isNotStudent == true) {
                        currentTransaction
                                = createTransaction(mainMenuSelection);
                        currentTransactionController
                                = new TransferController(currentTransaction, keypad, screen);
                        currentTransactionController.run(); // execute transaction
                    } else {
                        screen.displayMessageLine("\nYour account can't be used to "
                                + "do transfer.");
                    }
                    break;
                case PASSWORD:
                    break;
                case BANK_STATEMENT:
                    bsc = new BankStatementController(bankStatement, currentAccountNumber);
                    bsc.displayStatement();
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

        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
//            AdminController admin = new AdminController(bankDatabase, keypad, screen);
            // show main menu and get user selection
            int adminMenuSelection = adminController.displayMainMenu(currentAccountNumber);

            // decide how to proceed based on user's menu selection
            switch (adminMenuSelection) {
                // user chose to perform one of three transaction types
                case ADD_NASABAH:
                    adminController.AddNasabah();
                    break;
                case UNBLOCK:                    
                    System.out.println(bankDatabase.getAccountBlocked());

                    int number = adminView.displayUnblockMenu();
                    adminController.unblockNasabah(number);
                    bankDatabase.getAccount(number).unblockUser();
                    break;
                case VALIDATE:
                    break;
                case SEE_MONEY_DISPENSER:
                    showCashDispenser.showCashDispenser();
                    break;
                case ADD_MONEY_DISPENSER:
                    adminController.addCashDispenser();
                    break;
                case ADD_TANGGAL:
                    tanggalController.menuTanggal();
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

    // display the main menu and return an input selection
    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit\n");
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
        Transaction temp = null;

        switch (type) {
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(
                        currentAccountNumber, bankDatabase, bankStatement);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(
                        currentAccountNumber, bankDatabase, cashDispenser, bankStatement);
                break;
            case DEPOSIT:
                temp = new Deposit(
                        currentAccountNumber, bankDatabase, depositSlot, bankStatement);
                break;
            case TRANSFER:
                temp = new Transfer(
                        currentAccountNumber, bankDatabase, cashDispenser, bankStatement);
                break;
        }

        return temp;
    }

    public boolean checkAccountType() {
        if (bankDatabase.getAccount(currentAccountNumber).getAccountType().equals("AccountStudent")) {
            return false;
        }
        return true;
    }
}
