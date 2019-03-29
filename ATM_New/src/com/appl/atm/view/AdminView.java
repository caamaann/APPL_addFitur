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
public class AdminView {

    Screen screen = new Screen();
    Keypad keypad = new Keypad();

    public void AddNasabah() {

    }

    public int displayAdminMenu() {
        screen.displayMessageLine("\nAdmin menu:");
        screen.displayMessageLine("1 - Add Nasabah");
        screen.displayMessageLine("2 - Unblock Nasabah");
        screen.displayMessageLine("3 - Validate Deposit");
        screen.displayMessageLine("4 - See Money Dispenser");
        screen.displayMessageLine("5 - Add Money Dispenser");
        screen.displayMessageLine("6 - Add Tanggal");
        screen.displayMessageLine("0 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }

    public int displayAddCashDispenser() {
        screen.displayMessage("\nSpecify the amount of $20 to be added: ");
        return keypad.getInput();
    }
}
