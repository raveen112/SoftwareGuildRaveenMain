/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.flooringmastery.ui;

import com.raveenm.flooringmastery.service.InvalidDateException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
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

    // BigDecimal for FlooringMastery
    @Override
    public BigDecimal readBigDecimalMin(String prompt, BigDecimal min) {
        boolean isInvalid = true;
        BigDecimal num = new BigDecimal("100");
        while (isInvalid) {
            try {
                this.print(prompt);
                num = userInput.nextBigDecimal();
                userInput.nextLine();
                if (num.compareTo(min) != -1) {
                    isInvalid = false;
                }

            } catch (InputMismatchException e) {
                userInput.nextLine();

            }

        }
        num = num.setScale(2, RoundingMode.HALF_UP);
        return num;

    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean isInvalid = true;
        BigDecimal num = new BigDecimal("0");
        while (isInvalid) {
            try {
                this.print(prompt);
                num = userInput.nextBigDecimal();
                userInput.nextLine();
                isInvalid = false;
            } catch (InputMismatchException e) {
                userInput.nextLine();
            }
        }
        num = num.setScale(2, RoundingMode.HALF_UP);
        return num;
    }

    @Override
    public LocalDate readFutureLocalDate(String prompt) {
        boolean isInvalid = true;
        LocalDate currentDate = LocalDate.now();
        LocalDate queryDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        while (isInvalid) {
            try {
                print(prompt);
                queryDate = LocalDate.parse(userInput.nextLine(), formatter);

                if (!currentDate.isBefore(queryDate)) {
                    print("Please enter a future date! -_-");
                } else {
                    isInvalid = false;
                }
            } catch (DateTimeParseException e) {
                print("Enter the valid date");
            }
        }
        return queryDate;
    }
    
    @Override
    public LocalDate readLocalDate(String prompt) {
        boolean isInvalid = true;
        LocalDate queryDate = LocalDate.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        while (isInvalid) {
            try {
                print(prompt);
                queryDate = LocalDate.parse(userInput.nextLine(), formatter);
                isInvalid = false;

            } catch (DateTimeParseException e) {
                print("Enter the valid date.");

            }
        }
        return queryDate;
    }
}
