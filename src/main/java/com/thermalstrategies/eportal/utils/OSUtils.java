/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

/**
 *
 * @author Stuart
 */
public final class OSUtils {

    private static String OS = null;

    public static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

//    public static boolean isUnix()
//    {
//
//    }

    public static void main(String[] args)
    {
         System.out.println(isWindows());
    }

}

