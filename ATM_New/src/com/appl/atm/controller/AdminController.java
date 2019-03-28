/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Account;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.view.AdminView;
import com.appl.atm.view.Keypad;
import com.appl.atm.view.Screen;

/**
 *
 * @author Imanda Syahrul R
 */
public class AdminController {
    
    private Keypad keypad; // reference to keypad
    private Screen screen;
    
    public int  displayMainMenu(int accountNumber){
        BankDatabase bankDatabase = new BankDatabase();
        
        int menu;
        Account account = bankDatabase.getAccount(accountNumber);
        
  
            AdminView admin = new AdminView();
            menu = admin.displayAdminMenu();
       
        return menu;
    }
    
    public AdminController(Keypad theKeypad, Screen theScreen) {
	keypad = theKeypad;
	screen = theScreen;
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

}
