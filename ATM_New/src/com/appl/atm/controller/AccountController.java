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
    BankDatabase bankDatabase = new BankDatabase();
    
    public int  displayMainMenu(int accountNumber){
        Account account = bankDatabase.getAccount(accountNumber);       
        AccountView accountView = new AccountView();
        int menu = accountView.displayMainMenu();
        if(account.getAccountType().equals("Siswa") && menu == 4 ){
          System.out.println("Invalid Selection .. \n Student Account cannot do Transfer");
          return displayMainMenu(accountNumber);
        }else{
          return menu;  
        }
          
    }
    
    public int  displayWithdrawalMenu(int accountNumber, int amount[]){
        Account account = bankDatabase.getAccount(accountNumber);
        AccountView accountView = new AccountView();
        int menu = accountView.displayWithdrawalMenu(amount);
        if(account.getAccountType().equals("Siswa") && menu > 1 ){
            System.out.println("Invalid Selection .. \n Student Account withdrawal limit is $20");
            return displayWithdrawalMenu(accountNumber,amount);
        }else if(account.getAccountType().equals("MasaDepan") && menu > 4){
            System.out.println("Invalid Selection .. \n Masa Depan Account withdrawal limit is $100");
            return displayWithdrawalMenu(accountNumber,amount);
        }else{
            return displayWithdrawalMenu(accountNumber,amount);
        }
    }
    
   
}
