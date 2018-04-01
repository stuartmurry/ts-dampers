/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.security;


import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Role;
import com.thermalstrategies.eportal.model.User;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 *
 * @author Stuart
 */
public class EPortalSecurityContext
{

    private static EPortalSecurityContext ePortalSecurityContext;

    private static EPortalSecurityContext getInstance()
    {
        if (ePortalSecurityContext == null)
        {
            ePortalSecurityContext = new EPortalSecurityContext();
        }
        return ePortalSecurityContext;
    }

    /******* This section must be refactored later into a separate class !
    This is just an exact copy found in the UserSession class ***********/
    /***
     * ROLE_USER, ROLE_ADMIN, and ROLE_TELLER
     * @return
     */
    public static boolean isAdmin()
    {
        boolean isRoleUser = getInstance().searchGrantedAuthorities(getInstance().getAuthentication().getAuthorities(), "ROLE_USER");
        boolean isRoleAdmin = getInstance().searchGrantedAuthorities(getInstance().getAuthentication().getAuthorities(), "ROLE_ADMIN");
        boolean isRoleTeller = getInstance().searchGrantedAuthorities(getInstance().getAuthentication().getAuthorities(), "ROLE_TELLER");
        return isRoleUser && isRoleAdmin && isRoleTeller;
    }

    /***
     * ROLE_USER, ROLE_TELLER
     * @return
     */
    public static boolean isEmployee()
    {
        boolean isRoleUser = getInstance().searchGrantedAuthorities(getInstance().getAuthentication().getAuthorities(), "ROLE_USER");
        boolean isRoleTeller = getInstance().searchGrantedAuthorities(getInstance().getAuthentication().getAuthorities(), "ROLE_TELLER");
        return isRoleUser && isRoleTeller;
    }

    /**
     * ROLE_USER and ROLE_SUPERVISOR
     * @return
     */
    public static boolean isInspector()
    {
        boolean isRoleUser = getInstance().searchGrantedAuthorities(getInstance().getAuthentication().getAuthorities(), "ROLE_USER");
        boolean isRoleSupervisor = getInstance().searchGrantedAuthorities(getInstance().getAuthentication().getAuthorities(), "ROLE_SUPERVISOR");
        return isRoleUser && isRoleSupervisor;
    }

    /**
     * ROLE_USER
     * @return
     */
    public static boolean isCustomer()
    {
        return getInstance().searchGrantedAuthorities(getInstance().getAuthentication().getAuthorities(), "ROLE_USER");
    }

    public static User getUser()
    {
        EPortalUserDetail userDetails = (EPortalUserDetail) getInstance().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public static Customer getCustomer()
    {
        EPortalUserDetail userDetails = (EPortalUserDetail) getInstance().getAuthentication().getPrincipal();
        return userDetails.getCustomer();
    }

    public static Role getRole()
    {
        EPortalUserDetail userDetails = (EPortalUserDetail) getInstance().getAuthentication().getPrincipal();
        return userDetails.getRole();
    }

    public static String getUserName()
    {
        EPortalUserDetail userDetails = (EPortalUserDetail) getInstance().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        return userName;
    }

    private Authentication getAuthentication()
    {
        SecurityContext ctx = SecurityContextHolder.getContext();
        return ctx.getAuthentication();
    }

    private boolean searchGrantedAuthorities(Collection<GrantedAuthority> gas, String authority)
    {
        for (GrantedAuthority ga : gas)
        {
            if (authority.equals(ga.getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
