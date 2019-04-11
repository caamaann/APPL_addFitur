/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;
import com.appl.atm.model.BankDatabase;
import com.appl.atm.model.BankStatement;
import com.appl.atm.view.BankStatementView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ACER
 */
public class BankStatementController {
   BankStatement bankStatement;
   int LoginAccountNumber;
   BankStatementView statementView  = new BankStatementView();     
   public BankStatementController(BankStatement theBankStatement,int theAccountNumber ){
       bankStatement = theBankStatement;
       LoginAccountNumber = theAccountNumber;
   }
    
    public void displayStatement(){
        statementView.display(bankStatement.getDataSize(), LoginAccountNumber, bankStatement.getAccountNumber(),
                bankStatement.getTransaction(), bankStatement.getAmount(), bankStatement.getDate());
    }
}
