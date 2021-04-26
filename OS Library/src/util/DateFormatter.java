/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author TANJA-PC
 */
public class DateFormatter {
    private static SimpleDateFormat sdfApp = new SimpleDateFormat("dd.MM.yyyy");
    private static SimpleDateFormat sdfDB = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdfTimeApp = new SimpleDateFormat("HH:mm");
    
    
    //Date je java.util.Date !
    public static Date stringToDateApp(String dateStr) throws Exception{
        try {
            Date date =  sdfApp.parse(dateStr);
            return date;
        } catch (ParseException ex) {
            throw new Exception("Neispravan format datuma!");
        }
    }
    
    public static String dateToStringApp(Date date){
        String dateStr =  sdfApp.format(date);
        return dateStr;
    }
    
    public static String dateToStringDB(Date date){
        String dateStr =  sdfDB.format(date);
        return dateStr;
    }
    
    public static Date stringToDateDB(String dateStr) throws Exception{
        try {
            Date date =  sdfDB.parse(dateStr);
            return date;
        } catch (ParseException ex) {
            throw new Exception("Neispravan format datuma!");
        }
    }
    
    public static Date stringToTimeApp(String timeStr) throws Exception{
        try {
            Date time =  sdfTimeApp.parse(timeStr);
            return time;
        } catch (ParseException ex) {
            throw new Exception("Neispravan format vremena!");
        }
    }
    
    public static String timeToStringApp(Date date){
        String timeStr =  sdfTimeApp.format(date);
        return timeStr;
    }
    
    
}
