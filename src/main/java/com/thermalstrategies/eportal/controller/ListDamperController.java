/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.CustomerManager;
import com.thermalstrategies.eportal.manager.DamperManager;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import com.thermalstrategies.eportal.utils.Page;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author smurry
 */
public class ListDamperController extends AbstractFormController {

    private HibernateTemplate template;
    private JdbcTemplate jdbcTemplate;

    public ListDamperController(SessionFactory sessionFactory, DataSource dataSource) {
        template = new HibernateTemplate(sessionFactory);
        jdbcTemplate = new JdbcTemplate(dataSource);
        setCommandClass(ListDamperFormBean.class);
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException arg2) throws Exception {
        ModelAndView mv = new ModelAndView("damperlistbycustomer");
        DamperManager damperManager = new DamperManager(template, jdbcTemplate);
        CustomerManager customerManager = new CustomerManager(template);
        CustTimer ct = new CustTimer("ListDamperController.handleRequest()", System.currentTimeMillis());

        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");
        Integer pageNum = CustomWebUtils.getRequestIntegerParameter(request, "pageNum");
        Integer level = CustomWebUtils.getRequestIntegerParameter(request, "level");
        Integer year = CustomWebUtils.getRequestIntegerParameter(request, "year");
        Integer status = CustomWebUtils.getRequestIntegerParameter(request, "status");
        String occupancy = request.getParameter("occupancy");
 
        if (status != null) {
            if (status != null) {
//                System.out.println("Status is not null, adding status object to memory");
                mv.addObject("status", status);
            } else {
                System.out.println("Status = null");
                status = null;
            }
        }

        if (year != null) {
            mv.addObject("year", year);
        }

        if (custId != null) {
            mv.addObject("custId", custId);
        }

        if (buildingId != null) {
            mv.addObject("buildingId", buildingId);
        } else {
            mv.addObject("buildingId", null);
        }

        if (floorId != null) {
            mv.addObject("floorId", floorId);
        } else {
            mv.addObject("floorId", null);
        }

        if (occupancy != null) {
            mv.addObject("occupancy", occupancy);
        } else {
            mv.addObject("occupancy", null);
        }

        Page queryResults;
//        System.out.println("*** PageNum = " + pageNum);
        
        if (pageNum == null) {
            // No page number was given so just return the entire set
            queryResults = damperManager.getDampertestList(occupancy, status, year, custId, buildingId, floorId, 0);
        } else {
            queryResults = damperManager.getDampertestList(occupancy, status, year, custId, buildingId, floorId, pageNum);
        }

        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = customerManager.getCustomerBuildingFloorList(custId);
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
