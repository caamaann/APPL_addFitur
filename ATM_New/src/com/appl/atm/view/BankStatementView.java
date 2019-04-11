/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

import com.appl.atm.controller.BankStatementController;
import com.appl.atm.model.BankStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class BankStatementView {
    
    public void display(int dataSize, int theAccountNumber, ArrayList accountNumber, ArrayList transaction, ArrayList amount, ArrayList tanggal)
    {
        for(int i = 0; i < dataSize; i++)
        {
            if ((int)accountNumber.get(i) != theAccountNumber){
                i++;
            }
            
            System.out.println("Tanggal Transaksi : " + tanggal.get(i));
            System.out.println("Account Number : " + accountNumber.get(i));
            System.out.println("Jenis Transaksi : " + transaction.get(i));
            System.out.println("amount : "+ amount.get(i));
            System.out.println("----------------------------------------");
            System.out.println();
        }
    }
}