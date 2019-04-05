/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Admin;
import com.appl.atm.model.Account;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.CashDispenser;
import com.appl.atm.view.AdminView;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import java.util.ArrayList;

/**
 *
 * @author Imanda Syahrul R
 */
public class AdminController {

    private Keypad keypad; // reference to keypad
    private Screen screen;
    private BankDatabase bankDatabase;
    private CashDispenser cashDispenser;

    AdminView admin = new AdminView();

    public int displayMainMenu(int accountNumber) {
        BankDatabase bankDatabase = new BankDatabase();

        int menu;
        Account account = bankDatabase.getAccount(accountNumber);

        menu = admin.displayAdminMenu();

        return menu;
    }

    public AdminController(BankDatabase theBankDatabase, CashDispenser theCashDispenser, Keypad theKeypad, Screen theScreen) {
        bankDatabase = theBankDatabase;
        keypad = theKeypad;
        screen = theScreen;
        cashDispenser = theCashDispenser;
    }

    /**
     * @return the keypad
     */
    public Keypad getKeypad() {
        return keypad;
    }

    /**
     * @return the screen
     */
    public Screen getScreen() {
        return screen;
    }

    void AddNasabah() {
        boolean accAvail = false;
        double theTotalBalance = 0, theAvailableBalance = 0;
        int theAccountNumber;
        int theAccountType;

        do {
            screen.displayMessageLine("\nChoose what type of user to create : ");
            screen.displayMessageLine("\n1.Bisnis");
            screen.displayMessageLine("\n2.Siswa");
            screen.displayMessageLine("\n3.Masa depan");
            screen.displayMessageLine("\nInput the number of your choice : ");
            theAccountType = keypad.getInput();
        } while (theAccountType < 1 || theAccountType > 3);

        do {
            screen.displayMessage("\nPlease input new user account number : ");
            theAccountNumber = keypad.getInput();
            boolean avail = bankDatabase.checkAvail(theAccountNumber);
            if (avail) {
                accAvail = true;
            } else {
                screen.displayMessageLine("\nFailed! Account number is already in use.");
            }
        } while (!accAvail);

        screen.displayMessage("\nPlease input new user pin : ");
        int thePIN = keypad.getInput();
        /* TODO : periksa apakah admin*/
        do {
            screen.displayMessageLine("\nPlease input new user starting balance : ");
            theTotalBalance = theAvailableBalance = keypad.getInput();
            if (theTotalBalance < 0) {
                screen.displayMessageLine("\nStarting balance cannot be negative!");
            }
        } while (theTotalBalance < 0);

        bankDatabase.incAccount(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance, theAccountType);
    }

    void addCashDispenser() {
        int amount = admin.displayAddCashDispenser();

        System.out.println("!" + cashDispenser.getCount());
        if (amount <= 0 || amount % 20 != 0) {
            screen.displayMessageLine("\nInvalid amount.");
        } else {
            cashDispenser.setCount(amount);
            screen.displayMessageLine("\nSuccessfully added to dispenser.");
        }
    }

    public void unblockNasabah(int noAkun) {
        int i, cari;
        for (i = 0; i < (int) getBlocked().size(); i++) {
            if (noAkun == getBlocked().get(i)) {
                for (cari = 0; cari < (int) getAccounts().size(); cari++) {
                    if (noAkun == getAccounts().get(i).getAccountNumber()) {
                        if (getAccounts().get(i).getAccountType().equals("AccountBussiness")) {
                            getAccounts().get(i).setUNBLOCK_COST(3);
                            getAccounts().get(i).setTotalBalance(getAccounts().get(i).getTotalBalance() - getAccounts().get(i).getUnblockCost());
                            return;
                        } else if (getAccounts().get(i).getAccountType().equals("AccountMasaDepan")) {
                            getAccounts().get(i).setUNBLOCK_COST(2);
                            getAccounts().get(i).setTotalBalance(getAccounts().get(i).getTotalBalance() - getAccounts().get(i).getUnblockCost());
                            return;
                        }
                    }
                }
            }
            getBlocked().remove(i);
            screen.displayMessageLine("Unblock Telah Berhasil");
        }
        
    }

    public ArrayList<Account> getAccounts() {
        return bankDatabase.getAccounts();
    }

    public ArrayList<Integer> getBlocked() {
        return bankDatabase.getAccountBlocked();
    }
}
