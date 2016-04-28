package helper;


import java.util.GregorianCalendar;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class calendar {
    
    public calendar(){
    
    }
    
    public String getDate(){
        GregorianCalendar calendar = new GregorianCalendar();
        String str = Integer.toString(calendar.get(Calendar.YEAR)).concat("-");
        str = str.concat(Integer.toString(calendar.get(Calendar.MONTH)).concat("-"));
        str = str.concat(Integer.toString(calendar.get(Calendar.DATE)));
        
        System.out.println(str);
        return str;
        
    }
    
    public String getTime(){
        GregorianCalendar calendar = new GregorianCalendar();
        String str = Integer.toString(calendar.get(Calendar.HOUR)).concat(":");
        str = str.concat(Integer.toString(calendar.get(Calendar.MINUTE)).concat(":"));
        str = str.concat(Integer.toString(calendar.get(Calendar.SECOND)));
        
        System.out.println(str);
        return str;
    }
    
    public static void main (String args[]){
        DateTime test = new DateTime();
        LocalDate b = new LocalDate();
//        test = test.minusMonths(3);
//        cal.minusMonths(3).toDate();
        System.out.println(b.toString());
    }
    
    
}
