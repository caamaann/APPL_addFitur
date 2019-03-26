/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

/**
 *
 * @author ACER
 */
public class SiswaView {
    Screen screen = new Screen();
    Keypad keypad = new Keypad();
    
    public int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Change PIN");
        screen.displayMessageLine("5 - Bank Statement");
        screen.displayMessageLine("0 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        int input = keypad.getInput(); 
        
        return input == 4 ? 5 : (input == 5 ? 6 : input); // return user's selection
        
    }

    public int displayWithdrawalMenu(){
        int input;
        screen.displayMessageLine("\nLimit Withdraw for Today is : $"+".");
        screen.displayMessageLine("\nWithdrawal Menu:");
        screen.displayMessageLine("1 - $20");
        screen.displayMessageLine("0 - Cancel transaction");
        screen.displayMessage("\nChoose a withdrawal amount: ");
        
        input = keypad.getInput();
        
        return (input > 1) ? displayWithdrawalMenu() : input; // get user input through keypad      
    }
}
