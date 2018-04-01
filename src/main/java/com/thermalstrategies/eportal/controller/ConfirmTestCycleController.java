/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.BuildingUnderTestBean;
import com.thermalstrategies.eportal.FloorUnderTestBean;
import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart Murry
 */
public class ConfirmTestCycleController implements Controller
{

    private ApplicationManager applicationManager;
    public ConfirmTestCycleController(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
    }
 
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("index");
        Integer testCycleId = CustomWebUtils.getRequestIntegerParameter(request, "id");
        
        applicationManager.getTestCycleManager().confirmTestCycle(testCycleId);
        
        
        List<BuildingUnderTestBean> buildingUnderTestList = applicationManager.getStatisticsManager().getBuildingUnderTestList();
        for (BuildingUnderTestBean bean : buildingUnderTestList) {
            
                List<FloorUnderTestBean> l = applicationManager.getStatisticsManager().getFloorUnderTestList(bean.getBuildingId());
                bean.setFloorUnderTestList(l);
        }
        mv.addObject("buildingUnderTestList", buildingUnderTestList);
        
        CustomWebUtils.setRoleInsideModel(mv);
        return mv;
    }

}
