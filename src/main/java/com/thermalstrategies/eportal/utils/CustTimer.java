/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.utils;

import java.util.Calendar;

/**
 *
 * @author Stuart
 */
public class CustTimer
{
    private long startTimeInSeconds;
    private String nameOfProcess;
    public CustTimer(String nameOfProcess, long startTimeInSeconds)
    {
        this.startTimeInSeconds = startTimeInSeconds;
        this.nameOfProcess = nameOfProcess;
    }

    public String calculateTotalProcessTime()
    {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis() - startTimeInSeconds);
        return nameOfProcess + " took: " + c.get(Calendar.SECOND) + "." + c.get(Calendar.MILLISECOND) + " seconds";
    }


}
