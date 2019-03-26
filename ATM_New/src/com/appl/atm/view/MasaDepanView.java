/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

import com.appl.atm.model.Account;

/**
 *
 * @author ACER
 */
public class MasaDepanView {
    private Screen screen = new Screen(); // ATM's screen
    private Keypad keypad = new Keypad(); 

    
    public int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Transfer");
        screen.displayMessageLine("5 - Change PIN");
        screen.displayMessageLine("6 - Bank Statement");
        screen.displayMessageLine("0 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        
        return keypad.getInput(); // return user's selection
    }
    
   
    public int displayWithdrawalMenu(){
        int input;
        
        screen.displayMessageLine("\nLimit Withdraw for Today is : $"+".");
        screen.displayMessageLine("\nWithdrawal Menu:");
        screen.displayMessageLine("1 - $20");
        screen.displayMessageLine("2 - $40");
        screen.displayMessageLine("3 - $60");
        screen.displayMessageLine("4 - $100");
        screen.displayMessageLine("0 - Cancel transaction");
        screen.displayMessage("\nChoose a withdrawal amount: ");
        
        input = keypad.getInput();
        
        return (input > 4) ? displayWithdrawalMenu() : input; // get user input through keypad   
           
    }
}
