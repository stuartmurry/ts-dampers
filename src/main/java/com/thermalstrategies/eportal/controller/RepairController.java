/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart
 */
public class RepairController implements Controller {

    private ApplicationManager applicationManager;

    public RepairController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("repairhistorywelcome");
        
        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = applicationManager.getCustomerManager().getCustomerBuildingFloorList();
        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);

        CustomWebUtils.setRoleInsideModel(mv);

        return mv;
    }
}
