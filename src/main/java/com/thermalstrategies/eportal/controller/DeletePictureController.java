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
public class DeletePictureController implements Controller
{
    private ApplicationManager applicationManager;
    public DeletePictureController(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");
        ModelAndView mv = new ModelAndView("success");
        try
        {
            applicationManager.getPictureManager().deletePicture(id);
            mv.addObject("message", "Picture successfully deleted.");
            request.getSession().setAttribute("error_site", true);
        }
        catch(Exception e)
        {
            mv.addObject("message", "Error occured while trying to delete your picture: " + e.getMessage());
        }
        return mv;
    }
    

}
