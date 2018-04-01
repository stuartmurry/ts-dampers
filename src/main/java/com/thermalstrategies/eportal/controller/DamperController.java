/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.CustomerManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart
 */
public class DamperController implements Controller {

    private HibernateTemplate template;

    public DamperController(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("damperwelcome");
//        List<DamperDBView> damperDBViewList = applicationManager.getCustomerManager().getCustomerSiteBuildingFloors();
//        System.out.println("damperDBView count: " + damperDBViewList.size());
//        mv.addObject("damperDBViewList", damperDBViewList);
        CustomerManager customerManager = new CustomerManager(template);
        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = customerManager.getCustomerBuildingFloorList();
        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);

        CustomWebUtils.setRoleInsideModel(mv);

        return mv;
    }
}
