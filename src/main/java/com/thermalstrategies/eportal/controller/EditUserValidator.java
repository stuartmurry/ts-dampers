/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.utils.AbstractValidator;
import org.springframework.validation.Errors;

/**
 *
 * @author Stuart
 */
public class EditUserValidator extends AbstractValidator<UserCommandBean>{

    @Override
    public void doValidate(UserCommandBean e, Errors errors) {

        if ("".equals( e.getEmail().trim()))
        {
             errors.rejectValue("email", "error.user.email");
        }

        if ("".equals( e.getUserName().trim()))
        {
             errors.rejectValue("userName", "error.user.username");
        }
        if ("".equals( e.getPassword().trim()))
        {
             errors.rejectValue("password", "error.user.password");
        }
        if ("".equals( e.getPasswordconfirm().trim()))
        {
             errors.rejectValue("password", "error.user.passwordconfirm");
        } else
        {
            // User entered a password confirm, lets check if they are equal
            if (!e.getPassword().equals(e.getPasswordconfirm()))
            {
                errors.reject("error.user.passwordmatchfailed");
            }
        }
        if ("".equals( e.getFirstName().trim()))
        {
             errors.rejectValue("firstName", "error.user.firstname");
        }
        if ("".equals( e.getLastName().trim()))
        {
             errors.rejectValue("lastName", "error.user.lastname");
        }
        if ("".equals( e.getAddress1().trim()))
        {
             errors.rejectValue("address1", "error.user.address1");
        }
        if ("".equals( e.getCity().trim()))
        {
             errors.rejectValue("city", "error.user.city");
        }
        if ("".equals( e.getState().trim()))
        {
             errors.rejectValue("state", "error.user.state");
        }
        if ("".equals( e.getZip().trim()))
        {
             errors.rejectValue("zip", "error.user.zip");
        }

        if (e.getRole_id().equals(0))
        {
            errors.rejectValue("role_id", "error.user.role");
        } else if(new Integer(400).equals(e.getRole_id())) {
            // User did enter a role, now we check to see if its a customer role
            // if its a customer role then we'll need to see if they selected a customer
            if (new Integer(0).equals(e.getCustomer_id()))
            {
                errors.rejectValue("customer_id", "error.user.customer");
            }
        }

        if ("".equals( e.getPhone().trim()))
        {
             errors.rejectValue("phone", "error.user.phone");
        }
    }

}
