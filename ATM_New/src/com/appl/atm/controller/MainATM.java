/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appl.atm.controller;

import java.text.ParseException;

/**
 *
 * @author Annazar
 */
public class MainATM {
    public static void main(String args[])throws ParseException
    {
	ATM atm = new ATM();
	atm.run();
    }
}