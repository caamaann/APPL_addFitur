/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

/**
 *
 * @author m4nz2
 */
public class AccountStudent extends Account {

    public final int MAXWITHDRAW = 20;

    public AccountStudent(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN,
                theAvailableBalance, theTotalBalance);
    }

    public int getMaxWithdraw() {
        return MAXWITHDRAW;
    }
}
