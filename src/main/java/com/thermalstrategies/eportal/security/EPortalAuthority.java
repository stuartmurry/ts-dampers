/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.security;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Stuart
 */
public class EPortalAuthority implements GrantedAuthority {
    
    private String role;
    
    protected EPortalAuthority(String role)
    {
        this.role = role;
    }
            
    public String getAuthority() {
        return role;
    }
    
    // We're only using one role, not a list so it doesn't need to be sortable
    public int compareTo(Object o) {
        return 0;
    }

}
