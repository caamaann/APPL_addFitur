/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;


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
import static com.appl.atm.model.Constants.*;
import java.io.IOException;

/**
 *
 * @author Imanda Syahrul R
 */
public class AddNasabahController {
    private Keypad keypad; // reference to keypad
    private Screen screen;

    

    public int run() {
        boolean Admin = false, accAvail = false;
        double theTotalBalance = 0, theAvailableBalance = 0;
        int theAccountNumber;
        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        do {
            screen.displayMessageLine("\nChoose what type of user to create : ");
            screen.displayMessageLine("\n1.Admin");
            screen.displayMessageLine("\n2.Normal");
            screen.displayMessageLine("\nInput the number of your choice : ");
            theAccountNumber = keypad.getInput();
            if (theAccountNumber == 1) {
                Admin = true;
            } else if (theAccountNumber == 2) {
                Admin = false;
            } else {
                screen.displayMessageLine("\nThere's only two option, 1 or 2. There's no option " + theAccountNumber + ".");
            }
        } while(theAccountNumber<1 || theAccountNumber>2);
        
        do {
            screen.displayMessage("\nPlease input new user account number : ");
            theAccountNumber = keypad.getInput();
            boolean avail = bankDatabase.checkAvail(theAccountNumber);
            if (avail) {
                accAvail = true;
            } else {
                screen.displayMessageLine("\nFailed! Account number is already in use.");
            }
        } while(!accAvail);
        
        screen.displayMessage("\nPlease input new user pin : ");
        int thePIN = keypad.getInput();
        /* TODO : periksa apakah admin*/
        if (!Admin) {
            do {
                screen.displayMessageLine("\nPlease input new user starting balance : ");
                theTotalBalance = theAvailableBalance = keypad.getInput();
                if (theTotalBalance<0) {
                    screen.displayMessageLine("\nStarting balance cannot be negative!");
                }
            } while (theTotalBalance < 0);       
        }
        
        bankDatabase.incAccount(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance);
    }
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
