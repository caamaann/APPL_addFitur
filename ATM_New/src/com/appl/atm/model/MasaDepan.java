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

    private static final int MAXWITHDRAW = 100;
    private static final int MAXTRANSFER = 500;
    private static final int MONTHLY_ADM = 1;

    public MasaDepan(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN,
                theAvailableBalance, theTotalBalance);
    }

    /**
     * @return the MAXWITHDRAW
     */
    public static int getMAXWITHDRAW() {
        return MAXWITHDRAW;
    }

    /**
     * @return the MAXTRANSFER
     */
    public static int getMAXTRANSFER() {
        return MAXTRANSFER;
    }

    /**
     * @return the MONTHLY_ADM
     */
    public static int getMONTHLY_ADM() {
        return MONTHLY_ADM;
    }
    
    
}
