/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

import com.appl.atm.controller.BankStatementController;
import com.appl.atm.model.BankStatement;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ACER
 */
public class BankStatementView {
    
    public void displayBankStatement(int accountNumber) throws IOException{
    
    BankStatementController bankStatement = new BankStatementController();
    List<BankStatement> list = bankStatement.getList();
       
      if(!list.isEmpty()){
        int size;
        
        size = list.size();
        
        System.out.println();
        System.out.println("=========================================================================================================");
        System.out.println("Date\t\tDescription\tRef\tWithdrawal\tDeposit \tDeposit Valid\tBalance");
        System.out.println("=========================================================================================================");
        
        for(int i = 0;i < size;i++){
            if(list.get(i).getAccount() == accountNumber){
//               System.out.print(list.get(i).getIdStatement()+"\t");
                System.out.print(list.get(i).getDate()+"\t");
                System.out.print(list.get(i).getDescription()+"\t");
                
                int Ref = list.get(i).getRef();
                if(Ref != 0){
                    System.out.print(Ref);
                }else{
                    System.out.print("\t");
                }
                
                double Withdrawal = list.get(i).getWithdrawal();
                if(Withdrawal != 0){
                    System.out.print(Withdrawal+"\t\t");
                }else{
                    System.out.print("\t\t\t");
                }
                
                double Deposit = list.get(i).getDeposit();
                if(Deposit != 0){
                    System.out.print(Deposit+"\t\t");
                }else{
                    System.out.print("\t\t");
                }
                
                String depVal = list.get(i).getDepositValidate();
                if(depVal != null){
                    System.out.print(depVal+"\t\t");
                }else{
                    System.out.print("\t\t");
                }
                
                System.out.print(list.get(i).getBalance());
                System.out.println();
            }
        }
        
                            
        }else{
                System.out.println("empty");
                }
        
    }
}
