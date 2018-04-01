/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Stuart Murry
 */
public class ClientController implements Controller {
    private ApplicationManager applicationManager;

    public ClientController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        // Clear all sessions
        return new ModelAndView(new RedirectView("listclient.htm"));
    }

}
