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
public class SuccessController implements Controller {

    private ApplicationManager applicationManager;

    public SuccessController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String message = request.getParameter("message");
        ModelAndView mv = new ModelAndView("dampersuccess");

        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");

        mv.addObject("message", message);
        mv.addObject("custId", custId);
        mv.addObject("buildingId", buildingId);
        mv.addObject("floorId", floorId);

        List<DamperListBean> queryResults;
        queryResults = applicationManager.getDamperManager().getDamperListBeanList(custId, buildingId, floorId);
        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = applicationManager.getCustomerManager().getCustomerBuildingFloorList();
        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);
        mv.addObject("results", queryResults);

        CustomWebUtils.setRoleInsideModel(mv);
        return mv;
    }
}
