/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author Stuart Murry
 */
public class ListUserFormController extends SimpleFormController
{
    public static final String CREATE = "create";
    public static final String ACTION = "action";
    public static final String DELETE = "create";
    public static final String CANCEL = "cancel";
    public static final String ID = "id";
    
    private ApplicationManager applicationManager;
    public ListUserFormController(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
    }
    
    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors, Map controlModel) throws Exception 
    {
        return new ModelAndView("client/listbuilding");
		
    }
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception 
    {
        return null;
    }

}
