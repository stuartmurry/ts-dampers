/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.exception;

import org.springframework.dao.DataAccessException;

/**
 * 
 * @author Stuart
 */
public class DataAccessExceptionImpl extends DataAccessException
{
    
    public DataAccessExceptionImpl(String exception)
    {
        super( exception );
    }
}
