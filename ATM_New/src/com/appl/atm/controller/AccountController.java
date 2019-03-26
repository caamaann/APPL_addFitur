/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import com.appl.atm.model.Account;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.view.*;

/**
 *
 * @author ACER
 */
public class AccountController{
    
 
    public int  displayMainMenu(int accountNumber){
        BankDatabase bankDatabase = new BankDatabase();
        
        int menu;
        Account account = bankDatabase.getAccount(accountNumber);
        
        if(account.toString().equals("Bisnis")){
            BisnisView bisnis = new BisnisView();
            menu = bisnis.displayMainMenu();
        }else if(account.toString().equals("MasaDepan")){
            MasaDepanView masaDepan = new MasaDepanView();
            menu = masaDepan.displayMainMenu();
        }else{
            SiswaView siswa = new SiswaView();
            menu = siswa.displayMainMenu();
        }
        
        return menu;
    }
    
}
