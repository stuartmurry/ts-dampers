/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Helper for UsernameNotFoundException
 * @author Stuart
 */
public class UNameNotFoundException extends UsernameNotFoundException
{
    public UNameNotFoundException(String exception)
    {
        super(exception);
    }

}
