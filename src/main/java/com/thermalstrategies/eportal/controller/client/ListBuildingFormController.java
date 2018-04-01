/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Customer;
import java.util.ArrayList;
import java.util.List;
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
public class ListBuildingFormController extends SimpleFormController
{
    //public static final String CREATE = "create";
    //public static final String ACTION = "action";
    public static final String ADDBUILDING = "Add Building";
    public static final String CUSTOMERID = "customerid";
    public static final String CUSTOMERNAME = "customerName";
    //public static final String DELETE = "create";
    //public static final String CANCEL = "cancel";
    //public static final String ID = "id";
    
    private ApplicationManager applicationManager;
    public ListBuildingFormController(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
    }
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
            BuildingCommandBean command = new BuildingCommandBean();
            return command;
    }
    
    
    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors, Map controlModel) throws Exception 
    {
                
            Customer customer = (Customer)request.getSession().getAttribute("customer");
            List<Building> buildingList = new ArrayList<Building>();
            Integer customerId = customer.getId();
            if (customerId != null)
            {

                buildingList = applicationManager.getBuildingManager().getBuildingListFromCustomerId(customerId);
            }
            else 
            {
                buildingList = new ArrayList<Building>();
            }
            
            request.getSession().setAttribute("buildinglist", buildingList);
        
            return new ModelAndView("client/listbuilding");
        

    }
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception 
    {
        return null;
    }

}
