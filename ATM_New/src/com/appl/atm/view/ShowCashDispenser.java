/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;
import com.appl.atm.model.CashDispenser;
/**
 *
 * @author m4nz2
 */
public class ShowCashDispenser {
    private CashDispenser cashDispenser;
    
    public void showCashDispenser(){
        cashDispenser = new CashDispenser();
        System.out.println("Money in this Dispenser is: " + cashDispenser.getCount());
    }
}
