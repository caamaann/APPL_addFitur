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
public class AccountBussiness extends Account {

    public final int MAXWITHDRAW = 1000;
    public final int MAXTRANSFER = 10000;
    public final int MONTHLY_ADM = 5;

    public AccountBussiness(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance) {
        super(theAccountNumber, thePIN,
                theAvailableBalance, theTotalBalance);
    }

    @Override
    public int getMaxWithdraw() {
        return MAXWITHDRAW;
    }

    @Override
    public int getMaxTransfer() {
        return MAXTRANSFER;
    }

    @Override
    public int getMonthlyAdm() {
        return MONTHLY_ADM;
    }
}
