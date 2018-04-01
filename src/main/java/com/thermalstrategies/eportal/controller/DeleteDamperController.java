/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;


/**
 *
 * @author Stuart
 */
public class DeleteDamperController extends SuccessController {

    private ApplicationManager applicationManager;

    public DeleteDamperController(ApplicationManager applicationManager) {
        super(applicationManager);
        this.applicationManager = applicationManager;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");
        ModelAndView mv = new ModelAndView("success");
        try
        {
            applicationManager.getDamperManager().deleteDamper(id);
            mv.addObject("message", "Damper successfully deleted.");
            request.getSession().setAttribute("error_site", true);
        }
        catch(Exception e)
        {
            mv.addObject("message", "Error occured while trying to delete this damper: " + e.getMessage());
        }
        return mv;


    }
}
