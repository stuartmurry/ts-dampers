/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

/**
 *
 * @author Stuart
 */
public class CustomNumberUtils {

    public static Integer parseInt(Object o) {
        try {
            return Integer.parseInt("" + o);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer parseIntNullAsZero(Object o) {
        try {
            if ("null".equals("" + o))
            {
                return 0;
            }
            return Integer.parseInt("" + o);
        } catch (Exception e) {
            return null;
        }
    }
}
