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
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart
 */
public class ListRepairHistoryFromDamperListController extends AbstractListRepairHistoryController {

    public ApplicationManager applicationManager;

    public ListRepairHistoryFromDamperListController(ApplicationManager applicationManager) {
        super(applicationManager);
        this.applicationManager = applicationManager;
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException bindException) throws Exception {
//        ListRepairHistoryBean bean = (ListRepairHistoryBean) o; // Might need this later on...

        // We want the user to return to the exact page he was searching on
        // before he editted this damper.
        RedirectView redirectView;

        Integer level = CustomWebUtils.getRequestIntegerParameter(request, "level");
        Integer pageNum = CustomWebUtils.getRequestIntegerParameter(request, "pageNum");
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");

        if (level == null) {
            level = 3; // We need change the level the previous page on the damper type to reflect the level instead of guessing
        }

        switch (level) {
            case 1:
                redirectView = new RedirectView("listDampers.htm?level=" + level + "&pageNum=" + pageNum + "&custId=" + custId);
                break;
            case 2:
                redirectView = new RedirectView("listDampers.htm?level=" + level + "&pageNum=" + pageNum + "&custId=" + custId + "&buildingId=" + buildingId);
                break;
            case 3:
                redirectView = new RedirectView("listDampers.htm?level=" + level + "&pageNum=" + pageNum + "&custId=" + custId + "&buildingId=" + buildingId + "&floorId=" + floorId);
                break;
            default:
                return CustomWebUtils.getErrorModelAndView("Please specify a level");
        }

        return new ModelAndView(redirectView);

    }
}
