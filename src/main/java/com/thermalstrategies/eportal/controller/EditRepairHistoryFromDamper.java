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
public class EditRepairHistoryFromDamper extends AbstractEditRepairHistoryController {

    private ApplicationManager applicationManager;

    public EditRepairHistoryFromDamper(ApplicationManager applicationManager) {
        super(applicationManager);
        this.applicationManager = applicationManager;
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException exception) throws Exception {
        RepairHistoryBean bean = (RepairHistoryBean) command;
        ModelAndView mv = new ModelAndView("success");
//        ModelAndView mv = new ModelAndView(new RedirectView("listRepairHistory.htm?damperId=" + bean.getDamperId()));
        String save = request.getParameter("Save");
        if (save != null) {
            try {
                applicationManager.getRepairHistoryManager().saveRepairHistory(bean);
                mv.addObject("message", "Repair history successfully saved.");
            } catch (Exception e) {
                return CustomWebUtils.getErrorModelAndView("Error: Most likely this username already exists: returned from server: " + e.getMessage());
            }
        } else {
            mv.addObject("message", "Invalid Submit");
        }

        return mv;
    }
}
