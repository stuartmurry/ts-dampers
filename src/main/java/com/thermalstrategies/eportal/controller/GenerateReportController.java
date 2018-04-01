/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Buildingfloor;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author Stuart
 */
public class GenerateReportController extends SimpleFormController {

    private ApplicationManager applicationManager;

    public GenerateReportController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
//        setCommandClass(GenerateReportBean.class);
        setFormView("generatereport");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {

//        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");

        GenerateReportBean generateReportBean = new GenerateReportBean();

        List<Customer> customerList = applicationManager.getCustomerManager().getCustomerList();
        generateReportBean.setCustomerList(customerList);

        List<Building> buildingList = new ArrayList<Building>();
        try {
            Customer customer = customerList.get(0);
            buildingList = applicationManager.getBuildingManager().getBuildingListFromCustomerId(customer.getId());
        } catch (Exception e) {
            e.printStackTrace();
            // Do nothing...
        }
        generateReportBean.setBuildingList(buildingList);
        List<Buildingfloor> floorList = new ArrayList<Buildingfloor>();
        try {
            floorList = applicationManager.getBuildingManager().getFloorList(buildingList.get(0).getId());
        } catch (Exception e) {
            // Do nothing...
            e.printStackTrace();
        }
        generateReportBean.setFloorList(floorList);
        return generateReportBean;
    }

//    @Override
//    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, org.springframework.validation.BindException bindException) throws Exception {
//
//        ModelAndView mv = new ModelAndView("generatereport");
//
//        CustomWebUtils.setRoleInsideModel(mv);
//
//        return mv;
//    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException errors) throws Exception {
        GenerateReportBean generateReportBean = (GenerateReportBean) o;

        String generate = request.getParameter("generate");
        
        Integer customerId = generateReportBean.getCustomer_id();
        Integer buildingId = generateReportBean.getBuilding_id();
        Integer floorId = generateReportBean.getFloor_id();
        System.out.println("customer id: " + customerId);
        System.out.println("building id: " + buildingId);
        System.out.println("floor id: " + floorId);

        if (generate != null) {
            System.out.println("generating report");
            ModelAndView mv = new ModelAndView("generatereport");
            mv.addObject("command", generateReportBean);
            return mv;
        }

        System.out.println("refreshing report");
        ModelAndView mv = new ModelAndView("generatereport");
        
        List<Customer> customerList = applicationManager.getCustomerManager().getCustomerList();
        generateReportBean.setCustomerList(customerList);

        List<Building> buildingList = new ArrayList<Building>();
        try {
            buildingList = applicationManager.getBuildingManager().getBuildingListFromCustomerId(customerId);
        } catch (Exception e) {
            e.printStackTrace();
            // Do nothing...
        }
        generateReportBean.setBuildingList(buildingList);

        List<Buildingfloor> floorList = new ArrayList<Buildingfloor>();
        try {
            floorList = applicationManager.getBuildingManager().getFloorList(buildingId);
        } catch (Exception e) {
            // Do nothing...
            e.printStackTrace();
        }
        generateReportBean.setFloorList(floorList);

        mv.addObject("command", generateReportBean);
        return mv;

//        JasperPrint jasperPrint = applicationManager.getReportManager().generateDamperReport(request);
//
//        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());


    }
}
