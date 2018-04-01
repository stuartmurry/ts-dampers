/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.damper;

/**
 *
 * @author smurry
 */
public class DamperCommand
{

    public String toString()
    {
        return "["
                + "customer_id=" + customer_id
                + "]";
    }
    
    private int customer_id;

    /**
     * @return the customer_id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

}
