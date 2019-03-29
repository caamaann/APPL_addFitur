/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import com.appl.atm.view.*;
import java.util.ArrayList;

/**
 *
 * @author Annazar
 */
public class BankDatabase {

    private ArrayList<Account> accounts; // array of Accounts
    private ArrayList<Integer> accountBlocked;

    public BankDatabase() {
        accounts = new ArrayList<Account>();
        accounts.add(new Admin(00000, 00000, 0.0, 0.0));
        accounts.add(new AccountBussiness(1234, 4321, 1000.0, 1200.0));
        accounts.add(new AccountStudent(8765, 5678, 200.0, 200.0));
        accounts.add(new AccountBussiness(6665, 1234, 111700.0, 111900.0));
        accounts.add(new AccountMasaDepan(6666, 1234, 1700.0, 1900.0));
    }

    public Account getAccount(int accountNumber) {
        int i;
        for (i = 0; i < (int) accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber) {
                return getAccounts().get(i);
            }
        }
        return null; // if no matching account was found, return null
    }

    public Account getSpecificAccount(int userAccountNumber, int userPIN) {
        Account userAccount = getAccount(userAccountNumber);

        if (userAccount != null && userAccount.validatePIN(userPIN) && !userAccount.isUserBlocked()) {
            return userAccount;
        } else {
            return null; // account number not found, so return false
        }
    }

    public boolean authenticateAdmin(int userAccountNumber, int userPIN) {
        // attempt to retrieve the account with the account number
        Account userAccount = getAccount(userAccountNumber);

//      if account exists, return result of Account method validatePIN
        if (userAccount != null) {
            return (userAccount.validatePIN(userPIN) && getAccount(userAccountNumber).getAccountType().equals("Admin"));
        } else {
            return false; // account number not found, so return false
        }
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        // attempt to retrieve the account with the account number
        Account userAccount = getAccount(userAccountNumber);

//      if account exists, return result of Account method validatePIN
        if (userAccount != null && !userAccount.isUserBlocked()) {
            return userAccount.validatePIN(userPIN);
        } else {
            return false; // account number not found, so return false
        }
    }

    public boolean isUserExist(int userAccountNumber) {
        return getAccount(userAccountNumber) != null;
    }
    
    public void blockAccount(int noAkun){
        
        if(accountBlocked == null){
            accountBlocked = new ArrayList<Integer>();
            accountBlocked.add(noAkun);
        }
        else{
            accountBlocked.add(noAkun);
        }
        
        for(int i = 0; i < (int) accounts.size(); i++){
            if(accounts.get(i).getAccountNumber() == noAkun){
                accounts.get(i).blockUser();
                return;
            }
        }
    }
    
    public void changePIN(int noAkun){
        Screen screen = new Screen();
        Keypad keypad = new Keypad();
        
        int changed = 0;
        while(changed == 0){
            screen.displayMessage("Insert your new PIN : ");
            int pinBaru = keypad.getInput();
            
            for(int i = 0; i < (int) accounts.size(); i++){
                if(accounts.get(i).getAccountNumber() == noAkun){
                    if(accounts.get(i).getPin() == pinBaru)
                        screen.displayMessageLine("Your new PIN must be different from the current PIN");
                    else{
                        accounts.get(i).setPin(pinBaru);
                        screen.displayMessageLine("Your PIN has succesfully been changed..");
                        changed = 1;
                    }
                }
            }
        }
        
    }
    
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    
    public ArrayList<Integer> getAccountBlocked() {
        return accountBlocked;
    }
}
