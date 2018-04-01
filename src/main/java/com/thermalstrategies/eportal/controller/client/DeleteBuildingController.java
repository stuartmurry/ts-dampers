/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.NumberUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart Murry
 */
public class DeleteBuildingController implements Controller
{
    public static final String ID = "id";
    
    private ApplicationManager applicationManager;

    public DeleteBuildingController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = (Integer)NumberUtils.parseNumber(request.getParameter( ID ), Integer.class);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if ( id > 0 )
        {
            try
            {
                applicationManager.getBuildingManager().deleteBuilding(id);
                modelMap.put("client.delete.msg", "Deleted Building Successfully");
            }
            catch(Exception e)
            {
                modelMap.put("client.delete.msg", "Unable to Delete Building: " + e.getMessage());
                e.printStackTrace();
            }
            return new ModelAndView("client/deleteclient", modelMap);
            
        }

        return new ModelAndView(new RedirectView("listclient.htm"));
    }

}
