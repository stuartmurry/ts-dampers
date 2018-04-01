/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal;

import com.thermalstrategies.eportal.controller.PassFailBean;
import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author smurry
 */
public class IndexController implements Controller {

    private ApplicationManager applicationManager;

    public IndexController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Setup user information
//        User user = EPortalSecurityContext.getUser();
//        request.getSession().setAttribute("userContext", user);
        ModelAndView mv = new ModelAndView("index");

//        List<PassFailBean> passFailList = applicationManager.getPassFailManager().getCustomerPassFail();
//
//        System.out.println("Size: " + passFailList.size());
//        mv.addObject("passFailList", passFailList);

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
