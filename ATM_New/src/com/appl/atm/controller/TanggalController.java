/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;
import java.util.Calendar;
import java.util.Scanner;
/**
 *
 * @author m4nz2
 */
public class TanggalController {
    public static Calendar now = Calendar.getInstance();
    public void tanggalSekarang(){
//        this.now = Calendar.getInstance();
        System.out.println("Tanggal Sekarang: "
                + now.get(Calendar.DATE)
                + " - " + (now.get(Calendar.MONTH)+1)
                + " - " + now.get(Calendar.YEAR));
    }
    
    public void addTanggal(int tambahkanJumlahHari){
        now.add(Calendar.DATE, tambahkanJumlahHari);
        System.out.println("Tanggal Setelah ditambah " + tambahkanJumlahHari
                + " hari: "
                + now.get(Calendar.DATE)
                + " - " + (now.get(Calendar.MONTH)+1)
                + " - " + now.get(Calendar.YEAR));
    }
    
    public void menuTanggal(){
        int jumlahHari = 0, sumHari = 0, maxHari;     
        int cekTanggal = now.getActualMaximum(Calendar.DATE);
        Scanner scanner = new Scanner(System.in);
        tanggalSekarang();
        
        do{
             //menghitung jumlah maksimal hari yang dapat dimasukkan
             maxHari = cekTanggal - now.get(Calendar.DATE) + 3; 
             System.out.println("\nTidak Boleh lebih dari " + maxHari + " Hari");
             System.out.print("Masukkan jumlah hari: ");
             jumlahHari = scanner.nextInt();
             sumHari = now.get(Calendar.DATE) + jumlahHari - cekTanggal;
        }while ((sumHari > 3) || (jumlahHari < 0));
        addTanggal(jumlahHari);
    }
    
    public int getTanggal(){
        return TanggalController.now.get(Calendar.DATE);
    }
}
