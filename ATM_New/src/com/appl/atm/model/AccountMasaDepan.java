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
public class AccountMasaDepan extends Account {

    public final int MAXWITHDRAW = 100;
    public final int MAXTRANSFER = 500;
    public final int MONTHLY_ADM = 1;

    public AccountMasaDepan(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN,
                theAvailableBalance, theTotalBalance);
    }

    public int getMAXWITHDRAW() {
        return MAXWITHDRAW;
    }

    public int getMAXTRANSFER() {
        return MAXTRANSFER;
    }

    public int getMONTHLY_ADM() {
        return MONTHLY_ADM;
    }
}
