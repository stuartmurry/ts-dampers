/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author vmurry
 */
public class EditTestCycleController  extends SimpleFormController {

    private ApplicationManager applicationManager;

    public EditTestCycleController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        setCommandClass(TestCycleCommandBean.class);
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) throws Exception {
        System.out.println("show form...");
        ModelAndView mv = super.showForm(request, response, bindException);
        
        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");

        Boolean b = (Boolean) request.getSession().getAttribute("error_site");
        if (b != null && b==true) {
            request.getSession().setAttribute("error_site", false);
            return new ModelAndView("expired");
        }

        TestCycleCommandBean command = new TestCycleCommandBean();
        if (id != null){
              command = applicationManager.getTestCycleManager().getTestCycleCommand(id);
//            command = applicationManager.getSiteManager().getSite(id);
        }

        mv.setViewName("edittestcycle");
        CustomWebUtils.setRoleInsideModel(mv);
        mv.addObject("command", command);

        return mv;

    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException errors) throws Exception {
        //<a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${floor.floorName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
        System.out.println("Form Submission");
        ModelAndView mv = new ModelAndView("success");
        try {
        TestCycleCommandBean command = (TestCycleCommandBean)o;

        System.out.println("id:" + command.getId());
        System.out.println("custId:" + command.getCustId());
        System.out.println("buildingId:" + command.getBuildingId());
        System.out.println("Description:" + command.getDescription());
        System.out.println("Start Date:" + command.getsDate());
        System.out.println("Finish Date:" + command.getfDate());

            try {
                applicationManager.getTestCycleManager().saveTestCycleCommand(command);
            } catch (Exception e) {
                return CustomWebUtils.getErrorModelAndView("Error: Error occured when trying to save: : " + e.getMessage());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return mv;

    }

}
