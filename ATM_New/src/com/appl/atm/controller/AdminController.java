/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Account;
import static com.appl.atm.model.Constants.*;
import com.appl.atm.model.Admin;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;
import java.util.ArrayList;

/**
 *
 * @author Imanda Syahrul R
 */
public class AdminController {

    private BankDatabase bankDatabase;
    private Screen screen;
    private Keypad keypad;

    private ArrayList<Account> accounts;
    //private ArrayList<Integer> blockedUser;

    public AdminController(BankDatabase bank, Screen layar, Keypad papan) {
        bankDatabase = bank;
        screen = layar;
        keypad = papan;
        //accounts = bank;
    }

    public void unblockNasabah(int noAkun) {
        for (int i = 0; i < (int) getBlocked().size(); i++) {
            if (noAkun == getBlocked().get(i)) {

                for (int cari = 0; i < (int) getAccounts().size(); cari++) {
                    if (noAkun == getAccounts().get(i).getAccountNumber()) {
                        if (getAccounts().get(i).getAccountType().equals("AccountBussiness")) {
                            getAccounts().get(i).setUNBLOCK_COST(3);
                            getAccounts().get(i).setTotalBalance(getAccounts().get(i).getTotalBalance() - getAccounts().get(i).getUnblockCost());
                        } else if (getAccounts().get(i).getAccountType().equals("AccountMasaDepan")) {
                            getAccounts().get(i).setUNBLOCK_COST(2);
                            getAccounts().get(i).setTotalBalance(getAccounts().get(i).getTotalBalance() - getAccounts().get(i).getUnblockCost());
                        }
                    }
                }
            }

            return;
        }
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Integer> getBlocked() {
        return bankDatabase.getAccountBlocked();
    }

}
