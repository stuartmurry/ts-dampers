/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.security;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;




/**
 *
 * @author Stuart
 */
public class EPortalUserDetailsService implements UserDetailsService
{
    private ApplicationManager applicationManager;
    EPortalUserDetailsService(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
    }

    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException, DataAccessException {
        try{
            return applicationManager.getUserManager().getUserDetails(string);
        } catch(Exception e)
        {
            throw new UsernameNotFoundException(e.getMessage()){

            };
        }
    }
    
//    public UserDetails loadUserByUsername(String arg0) throws Exception
//    {
//        return applicationManager.getUserManager().getUserDetails(arg0);
//    }

}
