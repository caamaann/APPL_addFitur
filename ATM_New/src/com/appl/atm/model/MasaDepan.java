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
public class MasaDepan extends Account {

    public static final int MAXWITHDRAW = 100;
    public static final int MAXTRANSFER = 500;
    public static final int MONTHLY_ADM = 1;

    public MasaDepan(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN,
                theAvailableBalance, theTotalBalance);
    }
}
