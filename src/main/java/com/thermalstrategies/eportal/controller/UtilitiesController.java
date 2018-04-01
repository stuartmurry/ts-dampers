/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.controller.CustomerBuildingFloorBean.Building;
import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.model.Testcycle;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 *
 * @author Stuart
 */
public class UtilitiesController extends AbstractFormController {

    private ApplicationManager applicationManager;

    UtilitiesController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;

//        setSessionForm(true);
//        setValidateOnBinding(false);
//		setFormView("project/cedUser");
//        setCacheSeconds(1);
        setCommandClass(UtilitiesCommandBean.class);
//        setValidator(new EditUserControllerValidator());
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, org.springframework.validation.BindException bindException) throws Exception {
        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");
        ModelAndView mv = new ModelAndView("utilities");

//        UtilitiesCommandBean ucb ;
//        mv.addObject("command", ucb);
        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = applicationManager.getCustomerManager().getCustomerBuildingFloorList();
        List<Testcycle> testCycleList = applicationManager.getUtilitiesManager().getTestCycleList();

        //System.out.println("Testcycle Count: " + testCycleList.size());
        for (CustomerBuildingFloorBean cbfb : customerBuildingFloorBeanList) {
            for (Building building : cbfb.getBuildingList()) {
                //System.out.println(building.getBuildingName() + " - ");
                Integer buildingId = building.getId();
                for (Testcycle tc : testCycleList) {
                    //System.out.print(tc.getBuilding().getBuildingName() + ":");;
                    if (buildingId.equals(tc.getBuilding().getId())) {
                        System.out.println("**** Match ***: " + tc.getComplete());
                        if (!tc.getComplete()) {
                            building.setTest(true);
                        }
                    }
                }
                //System.out.println();
            }
        }
        mv.addObject("customerBuildingFloorBeanList", customerBuildingFloorBeanList);

        CustomWebUtils.setRoleInsideModel(mv);
        return mv;
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, org.springframework.validation.BindException errors) throws Exception {

        ModelAndView mv = new ModelAndView("success");

//        UserCommandBean userCommandBean = (UserCommandBean) command;
        String recalculateNextDate = request.getParameter("recalculateNextTestDate");

        if (recalculateNextDate != null) {
            System.out.println("recalculating next test date");
            try {
                applicationManager.getUtilitiesManager().recalulateNextTestDate();
            } catch (Exception e) {
                e.printStackTrace();
                return CustomWebUtils.getErrorModelAndView("Error: Unable to recalculate next test date: returned from server: " + e.getMessage());
            }

            mv.addObject("message", "Next test date successully recalculated");

//        } else if (startNewTestCycle != null) {
//            System.out.println("Starting new test cycle");
//            try {
//                applicationManager.getUtilitiesManager().recalulateNextTestDate();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return CustomWebUtils.getErrorModelAndView("Error: Unable to start a new test cycle: returned from server: " + e.getMessage());
//            }
        }

        return mv;

    }
}
