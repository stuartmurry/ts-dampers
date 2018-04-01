package com.thermalstrategies.eportal.security;



import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Role;
import com.thermalstrategies.eportal.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 *
 * @author Stuart Murry
 */
public class EPortalUserDetail implements UserDetails, GrantedAuthority {

    private User user;
    private String name;
    
    @Override
    public String getAuthority() {
        return name;
    }
    private Role role;
    private Customer customer;
    private Integer customerId;
    
    private EPortalUserDetail(String name) {
        this.name = name;
    }
    
    public EPortalUserDetail(User user) {
        this.user = user;
        this.role = user.getRole();
        this.customer = user.getCustomer();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities()
    {

        List<GrantedAuthority> gas =new ArrayList<GrantedAuthority>();
        gas.add(new EPortalUserDetail("ROLE_USER"));
        gas.add(new EPortalUserDetail("ROLE_ADMIN"));
        
        return gas;


    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    private class GA implements GrantedAuthority
    {
        private String role;
        private GA(String role)
        {
            this.role = role;
        }
        public String getAuthority() {
            return role;
        }

        public int compareTo(Object o) {
            return role.compareTo(o.toString());
        }
    }

    public String getPassword() {
        return getUser().getPassword();
    }

    public String getUsername() {
        return getUser().getUserName();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return getUser().isIsenabled();
    }
}
