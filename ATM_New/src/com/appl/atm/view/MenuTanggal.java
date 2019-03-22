/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.view;

import com.appl.atm.controller.TanggalController;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author m4nz2
 */
public class MenuTanggal {

    private TanggalController tanggal;

    public void menuTanggal() {
        tanggal = new TanggalController();
        Calendar now = Calendar.getInstance();
        int jumlahHari = 0, sumHari = 0, maxHari;
        int cekTanggal = now.getActualMaximum(Calendar.DATE);
        Scanner scanner = new Scanner(System.in);
        tanggal.tanggalSekarang();

        do {
            //menghitung jumlah maksimal hari yang dapat dimasukkan
            maxHari = cekTanggal - now.get(Calendar.DATE) + 3;
            System.out.println("\nTidak Boleh lebih dari " + maxHari + " Hari");
            System.out.print("Masukkan jumlah hari: ");
            jumlahHari = scanner.nextInt();
            sumHari = now.get(Calendar.DATE) + jumlahHari - cekTanggal;
        } while ((sumHari > 3) || (jumlahHari < 0));
        tanggal.addTanggal(jumlahHari);
    }
}
