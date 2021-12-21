/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.datetimecodealong;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author ravee
 */
public class App {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        System.out.println(ld);
        
        ld = LocalDate.parse("2015-01-01");
        System.out.println(ld);
        
        ld = LocalDate.parse("02/07/2021", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld);
        
        LocalDate past = ld.minusDays(8);
        String formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        past = ld.minusMonths(3);
        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        Period diff = ld.until(past); 
        System.out.println(diff);
        
        diff = past.until(ld);
        System.out.println(diff.getMonths());
        
        LocalDateTime ldt = LocalDateTime.now();
        formatted = ldt.format(DateTimeFormatter.ofPattern("yyy-MM-dd hh:mm:ss"));
        System.out.println(formatted);
        
        Date legacyDate = new Date();
        System.out.println(legacyDate);
        
        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);
        
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        ld = zdt.toLocalDate();
        System.out.println(ld);
    }
    
}
