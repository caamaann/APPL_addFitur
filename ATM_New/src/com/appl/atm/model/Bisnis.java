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
public class Bisnis extends Account {

    private static int MAXWITHDRAW = 1000;
    private static int MAXTRANSFER = 10000;
    private static int MONTHLY_ADM = 5;

    public Bisnis(int theAccountNumber, int thePIN,
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