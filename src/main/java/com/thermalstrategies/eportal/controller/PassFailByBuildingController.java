/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart
 */
public class PassFailByBuildingController implements Controller {

    private ApplicationManager applicationManager;

    public PassFailByBuildingController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("passfailbybuilding");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "id");
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        List<PassFailBean> passFailList = applicationManager.getStatisticsManager().getBuildingOverallPassFail(buildingId);

        mv.addObject("passFailList", passFailList);

        for (PassFailBean bean : passFailList) {
            mv.addObject("buildingName", bean.getBuildingName());
        }

        mv.addObject("custId", custId);
        mv.addObject("buildingId", buildingId);

        CustomWebUtils.setRoleInsideModel(mv);

        return mv;

    }

    
}
