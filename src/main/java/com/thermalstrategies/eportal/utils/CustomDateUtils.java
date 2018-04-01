/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Stuart
 */
public class CustomDateUtils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    private static SimpleDateFormat repairHistoryDateformatter = new SimpleDateFormat("M-dd-yy");//6-30-07
    private static SimpleDateFormat sqlformatter = new SimpleDateFormat("yyyy-MM-dd");

    public static String convertJasonMoonsDateToString(Date date) {
        String result= formatter.format(new Date());
        try { 
            result = formatter.format(date);
        } catch(Exception e) {
        }
        return result;
    }

    public static String repairHistoryDateformatter(Date date) {
        String str = "<Undefined>";
        try {
            System.out.println(date);
            str = repairHistoryDateformatter.format(date);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return str;
    }

    public static Date convertSQLDate(Object date) {
        try {
            return sqlformatter.parse("" + date);
        } catch (Exception e) {
//            e.printStackTrace();
            return new Date();
        }
    }

    public static Date convertJasonMoonsStringToDate(String date) {
        try {
            return formatter.parse(date);
        } catch (Exception e) {
            return new Date();
        }
    }
}
