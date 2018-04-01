/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomNumberUtils;
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
public class DeleteFloorController implements Controller {

    public static final String ID = "id";
    private ApplicationManager applicationManager;

    public DeleteFloorController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("success");
        Integer id = CustomNumberUtils.parseInt(request.getParameter(ID));
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (id != null && id > 0) {
            try {

                applicationManager.getBuildingManager().deleteFloor(id);
                modelMap.put("client.delete.msg", "Deleted Floor Successfully");
            } catch (Exception e) {
                modelMap.put("client.delete.msg", "Unable to Delete Floor: " + e.getMessage());
            }
        } else {
            modelMap.put("client.delete.msg", "Unable to Delete Floor for unknown reasons");
        }
        return new ModelAndView("client/deleteclient", modelMap);
    }
}
