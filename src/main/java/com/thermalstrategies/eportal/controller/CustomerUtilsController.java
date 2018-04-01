/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author vmurry
 */
public class CustomerUtilsController extends MultiActionController {

    private ApplicationManager applicationManager;

    public CustomerUtilsController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView id(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            PrintWriter pw = response.getWriter();
            // This will return the customer ID by default.
            if (EPortalSecurityContext.getRole().getRole().equals("customer")) {
                Customer customer = applicationManager.getCustomerManager().getCustomer(0);
                pw.print(customer.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
