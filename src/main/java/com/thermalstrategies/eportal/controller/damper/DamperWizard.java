/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.damper;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Customer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 *
 * @author Stuart
 */
public class DamperWizard extends AbstractFormController{

    public ApplicationManager dao;
    public DamperWizard(ApplicationManager dao)
    {
        this.dao = dao;
    }
    @Override
    protected ModelAndView showForm(HttpServletRequest arg0, HttpServletResponse arg1, BindException arg2) throws Exception {
        ModelAndView mv = new ModelAndView("damper/intro");
        return mv;
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException bindException) throws Exception {
        // Page 0
        // Page 1

        ModelAndView mv = new ModelAndView("damper/_1");
        List<Customer> customerList = dao.getCustomerManager().getCustomerList();
        mv.addObject("customerlist", customerList);
        return mv;

    }

}
