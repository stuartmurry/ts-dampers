/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart
 */
public class ListRepairHistoryFromIndexController extends AbstractListRepairHistoryController
{
    public ApplicationManager applicationManager;
    public ListRepairHistoryFromIndexController(ApplicationManager applicationManager) 
    {
        super(applicationManager);
        this.applicationManager = applicationManager;
    }
    
    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException bindException) throws Exception
    {

        return new ModelAndView(new RedirectView("index.htm"));

    }

}
