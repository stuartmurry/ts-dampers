/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.User;
import java.util.List;

/**
 *
 * @author Stuart
 */
public class UserCommandBean extends User
{

     private List<Customer> customerList;
     private Integer customer_id;
     private Integer role_id;
     private String passwordconfirm;
     private List<CustomerSelect> customerSelectList;

    /**
     * @return the customerList
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * @param customerList the customerList to set
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * @return the customer_id
     */
    public Integer getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the passwordconfirm
     */
    public String getPasswordconfirm() {
        return passwordconfirm;
    }

    /**
     * @param passwordconfirm the passwordconfirm to set
     */
    public void setPasswordconfirm(String passwordconfirm) {
        this.passwordconfirm = passwordconfirm;
    }

    /**
     * @return the role_id
     */
    public Integer getRole_id() {
        return role_id;
    }

    /**
     * @param role_id the role_id to set
     */
    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    /**
     * @return the customerSelectList
     */
    public List<CustomerSelect> getCustomerSelectList() {
        return customerSelectList;
    }

    /**
     * @param customerSelectList the customerSelectList to set
     */
    public void setCustomerSelectList(List<CustomerSelect> customerSelectList) {
        this.customerSelectList = customerSelectList;
    }


    public class CustomerSelect
    {
        private String name;
        private String id;
        private boolean checked;


        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the checked
         */
        public boolean isChecked() {
            return checked;
        }

        /**
         * @param checked the checked to set
         */
        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }

}
