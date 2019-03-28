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
   private List<BankStatement> list = new ArrayList<>();
   BankStatementView bankStatementView = new BankStatementView();
   
    public void setBankStatement(int account, String description, int ref, double withdrawal, double deposit, String depVal) {
       BankDatabase bankDatabase = new BankDatabase();
       BankStatement tr = new BankStatement();
       TanggalController tgl = new TanggalController();
       tr.setIdStatement(list.size());
       tr.setAccount(account);
       tr.setDate(tgl.getDate());
       tr.setDeposit(deposit);
       tr.setDepositValidate(depVal);
       tr.setDescription(description);
       tr.setRef(ref);
       tr.setWithdrawal(withdrawal);
       tr.setBalance(bankDatabase.getAccount(account).getAvailableBalance());
       this.list.add(tr);
   }
   
   public List<BankStatement> getList() throws IOException {
        if (this.list.isEmpty()) {
            return null;
        }
        return this.list;
    }
    
   
   public void displayBankStatement(int accountNumber) throws IOException{
       bankStatementView.displayBankStatement(accountNumber);
   }
}
