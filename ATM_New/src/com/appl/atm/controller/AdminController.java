/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Account;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.view.AdminView;

/**
 *
 * @author Imanda Syahrul R
 */
public class AdminController {
    
    public int  displayMainMenu(int accountNumber){
        BankDatabase bankDatabase = new BankDatabase();
        
        int menu;
        Account account = bankDatabase.getAccount(accountNumber);
        
  
            AdminView admin = new AdminView();
            menu = admin.displayAdminMenu();
       
        return menu;
    }
}
