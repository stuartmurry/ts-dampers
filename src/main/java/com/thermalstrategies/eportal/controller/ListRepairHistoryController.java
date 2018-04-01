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
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart
 */
public class ListRepairHistoryController extends AbstractFormController
{
    public ApplicationManager applicationManager;
    public ListRepairHistoryController(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
        setCommandClass(ListRepairHistoryBean.class);

    }
    
    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException bindException) throws Exception
    {

        return new ModelAndView(new RedirectView("index.htm"));

    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) throws Exception {

        ModelAndView mv = new ModelAndView("listrepairhistory");

        Integer damperId = CustomWebUtils.getRequestIntegerParameter(request, "damperId");
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");
        Integer pageNum = CustomWebUtils.getRequestIntegerParameter(request, "pageNum");
        Integer level = CustomWebUtils.getRequestIntegerParameter(request, "level");
        String back = request.getParameter("back");

        mv.addObject("damperId", damperId);
        mv.addObject("custId", custId);
        mv.addObject("buildingId", buildingId);
        mv.addObject("floorId", floorId);
        mv.addObject("pageNum", pageNum);
        mv.addObject("level", level);
        mv.addObject("back", back);

//        ListRepairHistoryBean command = new ListRepairHistoryBean();
//        mv.addObject("command", command);

        // Used for menu items
        List<RepairHistoryBean> list = applicationManager.getRepairHistoryManager().getRepairHistoryList(custId, null, buildingId, floorId);
        mv.addObject("repairHistoryList", list);

//        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = applicationManager.getCustomerManager().getCustomerBuildingFloorList();
//        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);

        CustomWebUtils.setRoleInsideModel(mv);

        return mv;

    }

}
