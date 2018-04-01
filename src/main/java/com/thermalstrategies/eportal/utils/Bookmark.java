/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Stuart
 */
public class Bookmark {

    public enum LOCATION {

        DAMPER_LIST, DAMPER_TEST, RETURN_POINT
    }

    public static void setLocation(LOCATION location, HttpServletRequest request) {
        String uri = request.getRequestURI().split("/")[2];
        request.getSession().setAttribute(location.name(), uri + "?" + request.getQueryString());
    }

    /**
     * Return the location store by #linksetLocation
     * Session could expire so location will return "index.htm".   Going to need to
     * probably use persistent storage on this one.
     * @param location
     * @param request
     * @return
     */
    public static String getLocation(LOCATION location, HttpServletRequest request) {
        
        try
        {
            return (String) request.getSession().getAttribute(location.name());
        }
        catch(Exception e)
        {
            System.out.println("Get Location Exception");
            e.printStackTrace();
            return "index.htm";
        }
        
    }
}
