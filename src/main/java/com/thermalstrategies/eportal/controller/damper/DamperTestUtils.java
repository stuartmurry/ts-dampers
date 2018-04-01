/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.damper;

/**
 *
 * @author Stuart
 */
public class DamperTestUtils
{
    public static String bytesToString(byte[] bytes)
    {
        if (bytes == null)
        {
             bytes = new String(" ").getBytes();
        }
        return new String( bytes );
    }

}
