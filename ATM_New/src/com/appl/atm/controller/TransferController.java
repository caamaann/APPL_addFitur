/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Transaction;
import com.appl.atm.model.Transfer;
import com.appl.atm.view.AccountView;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author Sophia Gianina Daeli
 */
public class TransferController extends TransactionController {

    private Transfer transaction;
    private int receiver;    
    
    AccountView accView = new AccountView();

    public TransferController(Transaction theTransaction, Keypad theKeypad, Screen theScreen) {
        super(theKeypad, theScreen);
        transaction = (Transfer) theTransaction;
    }

    @Override
    public int run() {
        int amount = displayMenuOfAmounts();
        receiver = accView.transferAskReceiverAccount();

        boolean destValid = receiverValidation();
        if (destValid == false) {
            getScreen().displayMessageLine("\nYour destination is invalid.");

            amount = 0; // further process will not be done
        }

        if (amount != 0) {
            transaction.setAmount(amount);
            int res = transaction.execute();

            if (res == WITHDRAW_SUCCESSFUL) {
                getScreen().displayMessageLine("\nTransfer successful.");
            } else if (res == BALANCE_NOT_ENOUGH) {
                getScreen().displayMessageLine("\nYour balance isn't enough for the transaction. "
                        + "Please enter another amount.");
            } else if (res == REACH_LIMIT) {
                getScreen().displayMessageLine("\nYou have exceed your transfer limit.");
            }
        }

        return 0;
    }
    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user chooses to cancel

    private int displayMenuOfAmounts() {
        int userChoice = -1; // local variable to store return value
        int aAmount; // (another amount) untuk pilihan input manual

        Screen screen = getScreen(); // get screen reference
        Keypad keypad = getKeypad();

        // array of amounts to correspond to menu numbers
        int[] amounts = {0, 20, 40, 60, 100, 200};

        // loop while no valid choice has been made
        while (userChoice == -1) {
            // display the withdrawal menu

            int input = accView.displayTransferMenu(amounts); // get user input through keypad

            // determine how to proceed based on the input value
            switch (input) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[input]; // save user's choice
                    break;
                case 6:
                    screen.displayMessage("\nInput amount: ");
                    aAmount = keypad.getInput();
                    if (aAmount <= 0 || aAmount % 20 != 0) {
                        screen.displayMessageLine("\nInvalid amount.");
                    } else {
                        userChoice = aAmount;
                    }
                    break;
                case WITHDRAWAL_CANCELED: // the user chose to cancel
                    userChoice = 0; // save user's choice
                    screen.displayMessageLine("Canceling transaction...");
                    break;
                default: // the user did not enter a value from 1-6
                    screen.displayMessageLine(
                            "Invalid selection. Try again.");
            }
        }

        return userChoice; // return withdrawal amount or CANCELED
    }

    public boolean receiverValidation() {
        if (receiver == transaction.getAccountNumber()) {
            return false;
        }
        return true;
    }
}
