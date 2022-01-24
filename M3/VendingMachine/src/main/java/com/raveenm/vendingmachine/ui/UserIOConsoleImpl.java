/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.vendingmachine.ui;

import java.util.Scanner;

/**
 *
 * @author ravee
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner userInput = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return userInput.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        String numString = userInput.nextLine();
        int num = Integer.parseInt(numString);
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {

        String numString;
        boolean isInvalidNum = true;
        int num = 0;
        while (isInvalidNum) {
            try {
                System.out.println(prompt);
                numString = userInput.nextLine();
                num = Integer.parseInt(numString);
                while (num < min || num > max) {
                    System.out.println("Enter a valid number.");
                    System.out.println(prompt);
                    numString = userInput.nextLine();
                    num = Integer.parseInt(numString);
                }
                isInvalidNum = false;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
        return num;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return userInput.nextDouble();
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double num = userInput.nextDouble();

        while (num < min | num > max) {
            System.out.println(prompt);
            num = userInput.nextDouble();
        }
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return userInput.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float num = userInput.nextFloat();

        while (num < min | num > max) {
            System.out.println(prompt);
            num = userInput.nextFloat();
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long num = userInput.nextLong();
        return num;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long num = userInput.nextLong();
        while (num < min || num > max) {
            System.out.println(prompt);
            num = userInput.nextLong();
        }
        return num;
    }
}
