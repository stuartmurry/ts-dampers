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
import org.springframework.web.servlet.mvc.AbstractFormController;

/**
 *
 * @author Stuart
 */
public abstract class AbstractEditRepairHistoryController extends AbstractFormController {

    private ApplicationManager applicationManager;

    public AbstractEditRepairHistoryController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        this.setCommandClass(RepairHistoryBean.class);
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException exception) throws Exception {
        System.out.println("Showing Form...");
        ModelAndView mv = new ModelAndView("repairhistory");
        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");
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

        CustomWebUtils.setRoleInsideModel(mv);

        if (id != null) {
            RepairHistoryBean rh = null;
            try {
                rh = applicationManager.getRepairHistoryManager().getRepairHistory(id);
            } catch (Exception e) {
                return CustomWebUtils.getErrorModelAndView("This record was removed or never existed in the first place. :: " + e.getMessage());
            }
            if (rh == null) {
                return CustomWebUtils.getErrorModelAndView("This record was removed or never existed in the first place.");
            }
            mv.addObject("command", rh);
            mv.addObject("damperId", damperId);// If this came from a damper we need to know.  So pass it on thru.
            mv.addObject("back", back);
            return mv;

        } else {
            return CustomWebUtils.getErrorModelAndView("Error retrieving repair history object.  Please supply an id.");
        }
    }
}
