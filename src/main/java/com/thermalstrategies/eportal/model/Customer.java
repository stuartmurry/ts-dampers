package com.thermalstrategies.eportal.model;
// Generated Oct 7, 2009 7:19:35 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Customer generated by hbm2java
 */
public class Customer  implements java.io.Serializable {


     private Integer id;
     private String customerName;
     private boolean isenabled;
     private Set<Customer> customers = new HashSet<Customer>(0);
     private Set<User> users = new HashSet<User>(0);
     private Set<Dampertest> dampertests = new HashSet<Dampertest>(0);
     private Set<Building> buildings = new HashSet<Building>(0);

    public Customer() {
    }

	
    public Customer(Integer id, String customerName, boolean isenabled) {
        this.id = id;
        this.customerName = customerName;
        this.isenabled = isenabled;
    }
    public Customer(Integer id, String customerName, boolean isenabled, Set<Customer> customers, Set<User> users, Set<Dampertest> dampertests, Set<Building> buildings) {
       this.id = id;
       this.customerName = customerName;
       this.isenabled = isenabled;
       this.customers = customers;
       this.users = users;
       this.dampertests = dampertests;
       this.buildings = buildings;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCustomerName() {
        return this.customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public boolean isIsenabled() {
        return this.isenabled;
    }
    
    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
    
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public Set<Dampertest> getDampertests() {
        return this.dampertests;
    }
    
    public void setDampertests(Set<Dampertest> dampertests) {
        this.dampertests = dampertests;
    }
    public Set<Building> getBuildings() {
        return this.buildings;
    }
    
    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }

    /**
     * @return the customers
     */
    public Set<Customer> getCustomers() {
        return customers;
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }


}


