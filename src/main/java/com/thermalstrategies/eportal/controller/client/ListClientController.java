/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.controller.CustomerBuildingFloorBean;
import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart Murry
 */
public class ListClientController extends SimpleFormController
{
    public static final String CREATE = "create";
    private ApplicationManager applicationManager;
    public ListClientController(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
    }
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception 
    {
        ClientCommandBean command = new ClientCommandBean();
	return command;
    }
    

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors, Map controlModel) throws Exception 
    {
        ModelAndView mv = new ModelAndView("client/listclient");
        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = applicationManager.getCustomerManager().getCustomerBuildingFloorList();
        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);
        CustomWebUtils.setRoleInsideModel(mv);
        return mv;
    }
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception 
    {
        boolean create = request.getParameter( CREATE ) != null;
        if ( create )
        {
             return new ModelAndView(new RedirectView("editclient.htm"));
        }
        return new ModelAndView(new RedirectView("index.htm")); // They canceled so start over
       
    }

}
