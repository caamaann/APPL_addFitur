/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.model;

import java.util.ArrayList;

/**
 *
 * @author Annazar
 */
public class BankDatabase {
    
    private ArrayList<Account> accounts; // array of Accounts
    
    public BankDatabase() {
        accounts = new ArrayList<Account>();
	accounts.add(new MasaDepan(1234, 1234, 1000.0, 1200.0));
	accounts.add(new Bisnis(5678, 1234, 200.0, 200.0));
        accounts.add(new Siswa(4321, 1234, 200.0, 200.0));
    }
    
    public Account getAccount(int accountNumber) {
	int i;
        for (i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber) {
                return accounts.get(i);
            }
        } 
        return null; // if no matching account was found, return null
    }
    
    public int authenticateUser(int userAccountNumber, int userPIN)
    {
	Account userAccount = getAccount(userAccountNumber);
	
	if(userAccount != null)
	{
	    if(userAccount.getPin() == userPIN)
	    {
		return 1;
	    }
	    else
	    {
		return 2;
	    }
	}
	else
	{
	    return 2;
	}
    }
   
}