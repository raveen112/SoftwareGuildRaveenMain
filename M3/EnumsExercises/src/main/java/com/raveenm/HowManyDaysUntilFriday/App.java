/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raveenm.HowManyDaysUntilFriday;

import com.raveenm.HowManyDaysUntilFriday.App.DayCalc;

/**
 *
 * @author ravee
 */
public class App {

  
    UserIO io = new UserIOConsoleImpl();
    public enum WeekDays{
        Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
    }
    
    public static void main(String[] args) {
        
        
        UserIO io = new UserIOConsoleImpl();
        
        String dayOfWeek = io.readString("Enter the day of the week: ");
        DayCalc(dayOfWeek);
        
        

        
        
    }
    
    public String class DayCalc{
        public String days(WeekDays dayOfWeek, String dayOnWeek){
            
        switch (dayOfWeek){
            case Monday:
                return "You got 4 days to GOO!!!";
            
            case Tuesday:
                return "You got 3 days to GOO!!!";
                
            case Wednesday:
                return "You got 2 days to GOO!!!"; 
            
            case Thursday:
                return "You got 1 more day!!!";
                
            case Friday:
                return "ITS THE WEEKEND!!!";
                
            case Saturday:
                return "You got 6 days to GOO!!!";    
                
            case Sunday:
                return "You got 5 days to GOO!!!";
            
            default:
                throw new UnsupportedOperationException();
            
             
        }
    }
    
}
}

