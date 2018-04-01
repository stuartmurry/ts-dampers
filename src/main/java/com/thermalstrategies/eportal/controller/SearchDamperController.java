/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import com.thermalstrategies.eportal.utils.Page;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @Deprecated
 * @author smurry
 */
public class SearchDamperController extends AbstractFormController {

    private ApplicationManager applicationManager;
    public SearchDamperController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        setCommandClass(ListDamperFormBean.class);
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException arg2) throws Exception {
        ModelAndView mv = new ModelAndView("damperlistbycustomer");

        CustTimer ct = new CustTimer("ListDamperController.handleRequest()" , System.currentTimeMillis());

        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");
        Integer pageNum = CustomWebUtils.getRequestIntegerParameter(request, "pageNum");
        Integer level = CustomWebUtils.getRequestIntegerParameter(request, "level");
        Integer year = CustomWebUtils.getRequestIntegerParameter(request, "year");
        String status = request.getParameter("status");
        String fullTextSearch = request.getParameter("fullTextSearch");

        System.out.println("Status: " + status);
//        if (custId == null)
//        {
//            System.out.println("user passed in parameter, but was unable to find id -> " + custId + "," + buildingId + "," + floorId);
//                return CustomWebUtils.getErrorModelAndView("Unable able to find this damper in the database. Please try again later.");
//        }

        if (status != null)
        {
            if (!"".equals(status.trim()))
            {
                System.out.println("Status is not null, adding status object to memory");
                mv.addObject("status", status);
            }
            else
            {
                System.out.println("Status = null");
                status = null;
            }
        }

        if (year != null)
        {
             mv.addObject("year", year);
        }

        if (custId != null)
        {
             mv.addObject("custId", custId);
        }

        if (buildingId != null)
        {
            mv.addObject("buildingId", buildingId);
        }
        else
        {
            mv.addObject("buildingId", null);
        }

        if (floorId != null)
        {
            mv.addObject("floorId", floorId);
        }
        else
        {
           mv.addObject("floorId", null);
        }

        Page queryResults;
//        System.out.println("*** PageNum = " + pageNum);
        if (pageNum == null){
            // No page number was given so just return the entire set
            queryResults = applicationManager.getDamperManager().getDampertestList(fullTextSearch, 0);
        } else {
            queryResults = applicationManager.getDamperManager().getDampertestList(fullTextSearch, pageNum);
        }

        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = applicationManager.getCustomerManager().getCustomerBuildingFloorList();
        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);

        mv.addObject("page", queryResults);
        mv.addObject("pageNum", pageNum == null ? "" : pageNum);
        mv.addObject("level", level);

        // We'll need to return back so lets bookmark this location
//        Bookmark.setLocation(LOCATION.RETURN_POINT, request);
        CustomWebUtils.setRoleInsideModel(mv);

        System.out.println(ct.calculateTotalProcessTime());
        return mv;
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException exception) throws Exception {

        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");
        Integer pageNum = CustomWebUtils.getRequestIntegerParameter(request, "pageNum");
        Integer level = CustomWebUtils.getRequestIntegerParameter(request, "level");

        ModelAndView mv = new ModelAndView(new RedirectView("listDampers.htm?custId=" + custId + "&buildingId=" + buildingId + "&floorId=" + floorId + "&pageNum=" + pageNum + "&level=" + level));
        

        return mv;
    }


}
