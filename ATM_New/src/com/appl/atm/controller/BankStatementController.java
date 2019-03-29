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
   public List<BankStatement> list = new ArrayList<>();
   ArrayList<BankStatement> statementList = new ArrayList<BankStatement>();
   BankStatementView bankStatementView = new BankStatementView();
   BankDatabase bankDatabase = new BankDatabase();
   
   private int generateIdStatement(){
        int size = this.statementList.size();
        if (size != 0){
            return size;
        }else{
            return 0;
        }
    }
   
   public void setBankStatement(int account, String description, int ref, double withdrawal, double deposit, String depVal) {
       System.out.println("\n masuk set list");
       
       BankStatement tr = new BankStatement();
       TanggalController tgl = new TanggalController();
       tr.setIdStatement(statementList.size());
       System.out.println("\n" +statementList.size());
       tr.setAccount(account);
       System.out.println("\n " + account);
       tr.setDate(tgl.getDate());
       System.out.println("\n " + ref);
       tr.setDeposit(deposit);
       System.out.println("\n " + deposit);
       tr.setDepositValidate(depVal);
        System.out.println("\n " + depVal);
       tr.setDescription(description);
        System.out.println("\n " + description);
       tr.setRef(ref);
        System.out.println("\n " + ref);
       tr.setWithdrawal(withdrawal);
        System.out.println("\n " + withdrawal);
       tr.setBalance(bankDatabase.getAccount(account).getAvailableBalance());
      System.out.println("\n " + bankDatabase.getAccount(account).getAvailableBalance());
      this.statementList.add(tr);
      
      for(int i = 0;i < statementList.size();i++){
            System.out.println("\n masukViewFor");
       
          
                System.out.println("\n masukIf2");
       
//               System.out.print(list.get(i).getIdStatement()+"\t");
                System.out.print(statementList.get(i).getDate()+"\t");
                System.out.print(statementList.get(i).getDescription()+"\t");
                
                int Ref = statementList.get(i).getRef();
                if(Ref != 0){
                    System.out.print(Ref);
                }else{
                    System.out.print("\t");
                }
                
                double Withdrawal = statementList.get(i).getWithdrawal();
                if(Withdrawal != 0){
                    System.out.print(Withdrawal+"\t\t");
                }else{
                    System.out.print("\t\t\t");
                }
                
                double Deposit = statementList.get(i).getDeposit();
                if(Deposit != 0){
                    System.out.print(Deposit+"\t\t");
                }else{
                    System.out.print("\t\t");
                }
                
                String despVal = statementList.get(i).getDepositValidate();
                if(despVal != null){
                    System.out.print(despVal+"\t\t");
                }else{
                    System.out.print("\t\t");
                }
                
                System.out.print(statementList.get(i).getBalance());
                System.out.println();
            }
         
   }
   
   public List<BankStatement> getList() throws IOException {
        if (this.statementList.isEmpty()) {
           System.out.println("\n masuk contorll null");
           return null;
           
        }
        
         System.out.println("\n masuk controll list");
        return this.statementList;
    }
    
   public void displayStatement(int accountNumber) throws IOException{
    
    BankStatementController bankStatement = new BankStatementController();
    List<BankStatement> sublist = bankStatement.getList();
    System.out.println("\n masukView");
          
      if(!sublist.isEmpty()){
        int size;
        System.out.println("\n masukViewIf");
       
        size = sublist.size();
        
        System.out.println();
        System.out.println("=========================================================================================================");
        System.out.println("Date\t\tDescription\tRef\tWithdrawal\tDeposit \tDeposit Valid\tBalance");
        System.out.println("=========================================================================================================");
        
        for(int i = 0;i < size;i++){
            System.out.println("\n masukViewFor");
       
            if(sublist.get(i).getAccount() == accountNumber){
                System.out.println("\n masukIf2");
       
//               System.out.print(list.get(i).getIdStatement()+"\t");
                System.out.print(sublist.get(i).getDate()+"\t");
                System.out.print(sublist.get(i).getDescription()+"\t");
                
                int Ref = sublist.get(i).getRef();
                if(Ref != 0){
                    System.out.print(Ref);
                }else{
                    System.out.print("\t");
                }
                
                double Withdrawal = sublist.get(i).getWithdrawal();
                if(Withdrawal != 0){
                    System.out.print(Withdrawal+"\t\t");
                }else{
                    System.out.print("\t\t\t");
                }
                
                double Deposit = sublist.get(i).getDeposit();
                if(Deposit != 0){
                    System.out.print(Deposit+"\t\t");
                }else{
                    System.out.print("\t\t");
                }
                
                String depVal = sublist.get(i).getDepositValidate();
                if(depVal != null){
                    System.out.print(depVal+"\t\t");
                }else{
                    System.out.print("\t\t");
                }
                
                System.out.print(sublist.get(i).getBalance());
                System.out.println();
            }
        }
        
                            
        }else{
                System.out.println("empty");
                }
        
    }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
//   public List<BankStatement> getListAccount(int accountNumber) throws IOException {
//        if (this.list.isEmpty()) {
//            return null;
//        }else{
//           List<BankStatement> newList;
//           
//           for (int i = 0 ; i < list.size(); i++){
//            
//           }
//           this.list.get(accountNumber)
//        }
//    }
   
   public void displayBankStatement(int accountNumber) throws IOException{
       System.out.println("\n masukController");
       System.out.println(statementList);
//       bankStatementView.displayBankStatement(accountNumber);
       displayStatement(accountNumber);
   }
}
