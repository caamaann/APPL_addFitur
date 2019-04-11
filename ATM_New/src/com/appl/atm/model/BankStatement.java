/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import com.appl.atm.controller.TanggalController;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class BankStatement {
     private int theAccountNumber;
    private ArrayList accountNumber;
    private ArrayList amount;
    private ArrayList transaction;
    private ArrayList date;
    private TanggalController tgl = new TanggalController();
    private int dataSize;

    public BankStatement(int theLoginAccountNumber) {
        theAccountNumber = theLoginAccountNumber;
        
        if(accountNumber == null)
            accountNumber = new ArrayList<>();

        if(amount == null)
            amount = new ArrayList<>();

        if(transaction == null)
            transaction = new ArrayList<>();
        
        if(date == null)
            date = new ArrayList<>();
    }
    
    public void addStatement(int theAccountNumber, int theAmount, String theTransaction)
    {

        getAccountNumber().add(theAccountNumber);
        getAmount().add(theAmount);
        getTransaction().add(theTransaction);
        getDate().add(tgl.getDateNow());
        dataSize += 1;
    }

    /**
     * @return the accountNumber
     */
    public ArrayList getAccountNumber() {
        return accountNumber;
    }

    /**
     * @return the amount
     */
    public ArrayList getAmount() {
        return amount;
    }

    /**
     * @return the transaction
     */
    public ArrayList getTransaction() {
        return transaction;
    }

    /**
     * @return the date
     */
    public ArrayList getDate() {
        return date;
    }
    
    public int getDataSize(){
        return dataSize;
    }
    
    
}
