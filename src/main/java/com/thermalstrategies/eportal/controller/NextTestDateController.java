/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart
 */
public class NextTestDateController implements Controller {

    private ApplicationManager applicationManager;

    public NextTestDateController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("nexttestdate");
//        Object list = request.getSession().getAttribute("customerList");
//        List<Customer> customerList = applicationManager.getCustomerManager().getCustomerList();
//        if (list == null) {
//            request.getSession().setAttribute("customerlist", customerList);
//        }

        List<Integer> yearList = new ArrayList<Integer>();
        Calendar tmp = Calendar.getInstance();
        yearList.add(tmp.get(Calendar.YEAR)); // Get this year and add
        for (int i = 1; i < 7; i++) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, i);
            yearList.add(c.get(Calendar.YEAR));
        }

        // Used for menu items - We'll implment later
//        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = applicationManager.getCustomerManager().getCustomerBuildingFloorList();
//        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);

        mv.addObject("yearList", yearList);
        mv.addObject("nextTestBeanList", applicationManager.getNextTestDateManager().getNextTestDateBeanList());
        CustomWebUtils.setRoleInsideModel(mv);
        return mv;

    }
}
